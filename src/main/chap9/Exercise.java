package main.chap9;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Exercise {

	private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/game_player.csv";
	private static final String OUTPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/output.csv";

	public static void main(String[] args) {

		Path in = Paths.get(INPUT_FILE); // 読み込み用のテキストファイル
		Path out = Paths.get(OUTPUT_FILE); // 書き込み用のテキストファイル
		
		while (true) {
			// inputファイルが存在しない場合、処理を終了する。
			if (!Files.exists(in)) {
				System.out.println("inputファイルが存在しません。");
				break;
			} else {
			//inputファイルが存在する場合、処理を進める。
				try {
					// 1)テキストファイルのすべての行をリストlinesに読み込む
					List<String> lines = Files.readAllLines(in);

					// 2)リストlinesを加工する
					for (int i = 0; i < lines.size(); i++) {
						
						// 2-1)文字列lineをカンマ区切りで要素数11の配列wordsに分割する
						String line = lines.get(i);
						// 2-1-1)要素数11の配列を事前に作成
						String[] words = new String[11];
						for (int j = 0; j < words.length; j++) {
							words[j] = "";
						}
						// 2-1-2)文字列lineを配列spreadedWordsに分割し、その要素数のみ配列words上書きする
						String[] spreadedWords = line.split(",");
						for (int j = 0; j < spreadedWords.length; j++) {
							words[j] = spreadedWords[j];
						}

						// 2-2)配列wordsを加工する。
						// 2-2-1)レベルが1のプレイヤーに、新人歓迎アイテム「応援旗」を追加する。
						// ただしプレイヤーはアイテムを4個までしか所持できないので、すでにアイテムを4個所持している場合は、代わりに所持金に100Gを加算する。
						if (Integer.parseInt(words[3]) == 1) {
							if (words[7].equals("") || words[8].equals("") || words[9].equals("")
									|| words[10].equals("")) {
								// アイテムに空きがある場合、応援旗を追加する。
								words[10] = "応援旗";
							} else {
								// アイテムに空きがない場合、所持金100Gを追加する。
								int possesingMoney = Integer.parseInt(words[4]);
								possesingMoney = possesingMoney + 100;
								words[4] = String.valueOf(possesingMoney);
							}
						}
						// 2-2-2)レベル10以下のプレイヤーに所持金100Gを追加する
						if (Integer.parseInt(words[3]) <= 10) {
							int possesingMoney = Integer.parseInt(words[4]);
							possesingMoney = possesingMoney + 100;
							words[4] = String.valueOf(possesingMoney);
						}
						// 2-2-3)アイテム「鋼玉」を持っている場合は、「スペシャルソード」と交換する。
						if (words[7].equals("鋼玉") || words[8].equals("鋼玉") || words[9].equals("鋼玉")
								|| words[10].equals("鋼玉")) {
							words[5] = "Sソード";
						}

						// 2-3)配列wordsをカンマ区切りの文字列lineに戻す。
						StringBuilder stringBuilder = new StringBuilder();
						for (int j = 0; j < words.length; j++) {
							if (j == 0) {
								stringBuilder.append(words[j]);
							} else {
								stringBuilder.append("," + words[j]);
							}
						}
						line = stringBuilder.toString();
						// System.out.println(line);
						lines.set(i, line);
					}

					// 3)加工済のlinesをoutputファイルに書き込む
					// 3-1)outputファイルが存在する場合
					if (Files.exists(out)) {
						System.out.println("ouputファイルがすでに存在します。");
						Scanner scanner = new Scanner(System.in);
						// 入力チェック（半角数字であること、0か1であること）
						while (true) {
							try {
								System.out.println("ファイルを上書きしますか? [1:YES/n:NO]");
								int input = scanner.nextInt();
								// 入力エラーがある場合
								if (input != 0 && input != 1) {
									throw new IllegalArgumentException("!！0/1以外の値が入力されています!！");
									// 入力エラーがない場合
								} else {
									// NOが入力された場合、ゲームを一度やめる
									if (input == 0) {
										System.out.println("作業を中断します。");
									} else {
										System.out.println("上書きします。");
										Files.write(out, lines);// writeメソッドでオプションを指定しない場合、上書きされる。
										System.out.println("作業が終了しました。");
									}
									break;// 入力チェックを元に再入力を促すループから抜ける。
								}
							} catch (InputMismatchException e) {
								System.out.println("!！半角数字以外が入力されています!！");
								scanner.next();// 入力バッファのクリア
							} catch (IllegalArgumentException e) {
								System.out.println(e.getMessage());
							}
						}

					// 3-2)outputファイルが存在しない場合
					} else {
						Files.write(out, lines, CREATE_NEW);// outputファイルを新規作成して、リストlines（加工済）の内容を書き込む
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			
		}
	}

}
