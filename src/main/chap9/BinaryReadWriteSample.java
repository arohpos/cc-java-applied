package main.chap9;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//4)バイナリファイルの読み書き
//単体実行により、original.pngを読み込む。original_output.pngを作成し、読み込んだ内容を書き込む。
public class BinaryReadWriteSample {
	
	private static final String INPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/original.png";
	private static final String OUTPUT_FILE = "/Applications/Eclipse_2022-03.app/Contents/workspace/cc-java-applied/src/main/chap9/original_output.png";
	
	public static void main(String[] args) {
		//try(){}catch{}でfinallyを入れなくても、closeしてくれる。
		try (//ここでストリームの生成
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(INPUT_FILE));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(OUTPUT_FILE));){
			byte[] buffer = new byte[1024];
			
			while(true) {
				//byte配列にデータをインプットストリームデータを読み込む
				int len = in.read(buffer);
				if(len == -1) {
					break;
			}
				//どのbyte配列（buffer）の何要素目（0）から、何要素（len）読み込むかを指定してアウトプットストリームにデータを書き込む。
				out.write(buffer, 0, len);
			}
			//アウトプットストリームのバッファに残る全てのデータを書き込む
			out.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
