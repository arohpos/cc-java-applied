package main.chap15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class WarGame implements Serializable {
	private static final long serialVersionUID = -8597415234573787559L;	

	private Dealer dealer;
	private Player player;
	private Player cpu;
	private int remainingCardsCoutnter;
	private int playingCardCounter;// 1ゲーム内で手札が切られた回数
	private int totalPlayingCounter;// ゲームが遊ばれた総回数//ここに関する実装が必要！
	private boolean continueWarGameFlg;

	private static WarGame warGame = new WarGame();

	// Singleton用のprivateコンストラクタ
	private WarGame() {
		
		this.dealer = Dealer.getInstance();
		this.player = new Player("player");
		this.cpu = new Player("cpu");
		this.remainingCardsCoutnter = 0;
		this.playingCardCounter = 0;
		this.totalPlayingCounter = 0;
		this.continueWarGameFlg = true;

	}

	public static WarGame getInstance() {
		return warGame;
	}

	// メイン処理
	public void playWarGame() {
		//
		Path gameProgressDataPath = Paths.get("/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/game_progress.dat");
		
		//saveデータが存在すればロード処理する
		if (Files.exists(gameProgressDataPath)) {
			System.out.println("ファイルまたはディレクトリは存在します");
			setWarGame(loadGameProgress());
		}else {
			// プレーヤーとCPUへカードを均等に配る
			this.dealCards();
		}
		Scanner scanner = new Scanner(System.in);

		// コンソールからqが入力されるまでWarGameを続ける
		while (this.continueWarGameFlg) {
			showGameProgress();
			// 入力チェック（dかqであること）
			while (true) {
				try {
					System.out.println("札を切りますか？ (d:札を切る, q:中断) >");
					String input = scanner.next();
					// 入力エラーがある場合
					if (!input.equals("d") && !input.equals("q")) {
						throw new IllegalArgumentException("dまたはqの文字を入力してください");
						// 入力エラーがない場合
					} else {
						// dが入力された場合、手札を切って勝負する
						if (input.equals("d")) {
							this.judgeCards();

							// qが入力された場合、WarGameをやめる
						} else {
							System.out.println("game is quitted");
							this.showGameProgress();
							WarGame.saveGameProgress(warGame);
							System.out.println("game progress is saved");
							this.continueWarGameFlg = false;
						}
						// 入力チェックを終了する
						break;
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			if (this.player.getDealtCards().size() == 0) {
				System.out.println("手札がなくなったのでゲームを終了します。");
				this.totalPlayingCounter = this.totalPlayingCounter + 1;
				this.continueWarGameFlg = false;
				this.showGameResult();
				//saveデータ削除処理
				WarGame.deleteGameProgress();
			}
		}
	}

	// プレーヤーとCPUそれぞれに13枚ずつカードを配る処理
	// Dealerのメソッドかも
	private void dealCards() {
		for (int i = 0; i < 13; i++) {
			try {
				// playerとcpuそれぞれの手札枠（ArrayList）に、dealerから配られたカードを格納する
				this.player.getDealtCards().add(dealer.deal());
				this.cpu.getDealtCards().add(dealer.deal());
			} catch (AllCardsAlreadyDealtException e) {
				e.printStackTrace();
			}
		}
		System.out.println("---ディーラーがカードの配布を終えました---");
	}

	// ゲームの進行状況を表示
	private void showGameProgress() {
		System.out.println("---------------------------------");
		System.out.println("今回のゲームで勝負された回数：" + this.playingCardCounter + "回");
		System.out.println("場に積まれた札数：" + this.remainingCardsCoutnter + "枚");
		System.out.println("あなたの持ち札数：" + player.getDealtCards().size() + "枚");
		System.out.println("あなたの獲得した札数：" + player.getGainedCardsNumber() + "枚");
		System.out.println("CPUの持ち札数：" + cpu.getDealtCards().size() + "枚");
		System.out.println("CPUの獲得した札数：" + cpu.getGainedCardsNumber() + "枚");
		System.out.println("---------------------------------");
	}

	// ゲームの最終結果を表示
	private void showGameResult() {
		System.out.println("---------------------------------");
		System.out.println("今回のゲームで勝負された回数：" + this.playingCardCounter + "回");
		System.out.println("あなたの獲得した札数：" + player.getGainedCardsNumber() + "枚");
		System.out.println("CPUの獲得した札数：" + cpu.getGainedCardsNumber() + "枚");
		if (player.getGainedCardsNumber() > cpu.getGainedCardsNumber()) {
			System.out.println("勝者：" + player.getName());
		} else if (player.getGainedCardsNumber() > cpu.getGainedCardsNumber()) {
			System.out.println("勝者：" + cpu.getName());
		} else {
			System.out.println("獲得枚数が同じため、ゲームは引き分けです");
		}
		System.out.println("---------------------------------");
	}

	// 勝負を行う
	private int judgeCards() {
		this.playingCardCounter = this.playingCardCounter + 1;
		System.out.println("### 第" + this.playingCardCounter + "回戦 ###");
		this.remainingCardsCoutnter = this.remainingCardsCoutnter + 2;
		System.out.println("この勝負に勝利したプレーヤーは" + this.remainingCardsCoutnter + "枚獲得します");

		int result = player.playCard().compareTo(cpu.playCard());
		switch (result) {
		case 1:
			this.handleWinEvent(player);
			break;
		case -1:
			this.handleWinEvent(cpu);
			break;
		case 0:
			System.out.println("引き分けです");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + result);
		}
		return result;
	}

	// どちらかのプレーヤーが勝利した際の処理
	private void handleWinEvent(Player winningPlayer) {
		System.out.println(winningPlayer.getName() + "の勝利！");
		System.out.println(winningPlayer.getName() + "が" + this.remainingCardsCoutnter + "枚のカードを獲得します");
		// 場に残っているカードを勝利したプレーヤーに渡す
		winningPlayer.setGainedCardsNumber(winningPlayer.getGainedCardsNumber() + this.remainingCardsCoutnter);
		this.remainingCardsCoutnter = 0;
	}

	//ゲーム経過保存処理
	public static void saveGameProgress(WarGame warGame) {
		try (FileOutputStream fos = new FileOutputStream(
				"/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/game_progress.dat");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(warGame);
			oos.flush();// 明示的な書き込み
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WarGame loadGameProgress() {
		WarGame warGame = null;
		try (FileInputStream fis = new FileInputStream(
				"/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/game_progress.dat");
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			warGame = (WarGame) ois.readObject();
			System.out.println(warGame);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return warGame;
	}
	
	public static void deleteGameProgress() {
		try {
			Files.delete(Paths.get("/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/game_progress.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setWarGame(WarGame warGame) {
		this.dealer = warGame.dealer;
		this.player = warGame.player;
		this.cpu = warGame.cpu;
		this.remainingCardsCoutnter = warGame.remainingCardsCoutnter;
		this.playingCardCounter = warGame.playingCardCounter;
		this.totalPlayingCounter = warGame.totalPlayingCounter;
		this.continueWarGameFlg = true;
	}

	@Override
	public String toString() {
		return "カードが" + this.remainingCardsCoutnter + "枚場に残っています。\r\n" + "このゲームにおいてカードの判定はすでに" + this.playingCardCounter
				+ "回行われています。";
	}

	
	

}
