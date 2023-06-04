package main.chap11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLSample {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("https://codecamp.jp/");
			
			try {
				InputStream is = url.openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				while(true) {
					String line = reader.readLine();
					if(line == null) {
						break;
					}
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		
		
	}

}
