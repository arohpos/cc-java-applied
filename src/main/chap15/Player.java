package main.chap15;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

	private static final long serialVersionUID = -1609870421125272660L;
	// プレイヤー名
	private String name;
	// プレイヤーに配られた手札
	private ArrayList<Card> dealtCards = new ArrayList<>();
	// プレイヤーが勝ち取ったカード枚数
	private int gainedCardsNumber;

	public Player(String name) {
		super();
		this.name = name;
		this.gainedCardsNumber = 0;
	}

	//手札を切るメソッド
	public Card playCard() {
		Card playedCard = this.dealtCards.get(dealtCards.size() - 1);
		System.out.println(this.name + " plays :" + playedCard);
		this.dealtCards.remove(this.dealtCards.size() - 1);
		return playedCard;
	};

//	public static void savePlayerProgress(Player player, Player cpu) {
//		try (FileOutputStream fos = new FileOutputStream(
//				"/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/player_progress.dat");
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//			oos.writeObject(player);
//			oos.writeObject(cpu);
//			oos.flush();// 明示的な書き込み
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//playerとcpuのtupleをreturn
//	public static ImmutablePair<Player, Player> loadPlayerProgress() {
//		Player player = null;
//		Player cpu = null;
//		try (FileInputStream fis = new FileInputStream(
//				"/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap15/player_progress.dat");
//				BufferedInputStream bis = new BufferedInputStream(fis);
//				ObjectInputStream ois = new ObjectInputStream(bis)) {
//			player = (Player) ois.readObject();
//			cpu = (Player) ois.readObject();
////			System.out.println(player);
////			System.out.println(cpu);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return ImmutablePair.of(player, cpu);
//	}

	@Override
	public String toString() {
		return this.name + "は、" + this.gainedCardsNumber + "カードを得ています。";
	}

	protected ArrayList<Card> getDealtCards() {
		return dealtCards;
	}

	protected String getName() {
		return name;
	}

	protected int getGainedCardsNumber() {
		return gainedCardsNumber;
	}

	protected void setGainedCardsNumber(int gainedCardsNumber) {
		this.gainedCardsNumber = gainedCardsNumber;
	}

}
