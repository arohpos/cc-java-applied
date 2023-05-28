package main.chap9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//1)テキストファイルの書込み
//単体実行によりtextWriteSample.txtが作成され、指定の内容が書き込まれる。
public class TextWriteSample {

	private static final String OUTPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/textWriteSample.txt";

	public static void main(String[] args) {
		
		//ここでoutを定義する理由は、finallyブロックでもoutを使用するためである。
		PrintWriter out = null;
		try {
		//処理の流れはFileWriterを作成して、BufferdWriter、PrintWriterと機能を拡張していくイメージ	
			
			//指定ファイルに対する書込みを行うFileWriterクラスのインスタンスを生成
			//注：本来はFileWriterのみで書込みが可能だが、ディスクI/O負荷が起きる。
			FileWriter fw = new FileWriter(OUTPUT_FILE, true);
			
			//ディスクI/O負荷を軽減するためにバッファリング書込みに対応したI/Oストリームのクラスに拡張する。
			BufferedWriter bw = new BufferedWriter(fw);
			
			//PrintWriterクラスに拡張することで、printメソッドを用いてファイル書込みが可能になる。
			out = new PrintWriter(bw);//書込む書式
			
			for(int i = 0; i < 10; i++) {
				out.printf("[%02d]テキストファイル書き込みテスト\r\n", i+1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			if(out != null) {
				out.close();
			}
			
		}
		
	}

}
