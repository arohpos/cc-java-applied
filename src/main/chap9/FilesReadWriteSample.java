package main.chap9;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
 
//6)Files.readAllFiles(), Files.write() を使ったテキストファイル読み書きのサンプル。
//単体実行により1)TextWriteSample.javaで作成されたtextWriteSample2.txtを読み込む。
//TextReadAndWriteSample.javaを作成し、読み込んだ内容を書き込む。

public class FilesReadWriteSample {
 
  private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/textWriteSample.txt";
  private static final String OUTPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/textReadAndWriteSample.txt";
 
  
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