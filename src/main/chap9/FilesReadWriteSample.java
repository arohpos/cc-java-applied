package main.chap9;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
 
/**
 * Files.readAllFiles(), Files.write() を使ったテキストファイル読み書きのサンプル。
 */
public class FilesReadWriteSample {
 
  /**
   * 読み込むテキストファイルの名前。
   * (環境に合わせて書き換えてください)
   */
  private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/sample.txt";
  private static final String OUTPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/sample2.txt";
 
  
  public static void main(String[] args) {
 
    Path in = Paths.get(INPUT_FILE); // 読み込み用のテキストファイル
    Path out = Paths.get(OUTPUT_FILE); // 書き込み用のテキストファイル
    try {

      // テキストファイルのすべての行を List に読み込む。
      List<String> lines = Files.readAllLines(in);
 
      // 読み込んだ行を画面に表示
      for (String line : lines) {
        System.out.println(line);
      }
 
      // lines の内容をテキストファイルに書き込む
      Files.write(out, lines, CREATE_NEW); // 2回めの実行では例外が発生する
 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}