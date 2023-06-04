package main.chap11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class socketSample {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("codecamp.jp", 80);
			
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			out.write("GET /index.html HTTP/1.\r\n".getBytes());
			out.write("HOST codecamp.jp\r\n".getBytes());
			out.write("\r\n".getBytes());
			out.flush();
			
			while(true) {
				String line = in.readLine();
				if(line == null) {
					break;
				}
				System.out.println(line);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
