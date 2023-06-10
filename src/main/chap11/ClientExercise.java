package main.chap11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientExercise {
	
	private static final String SERVER_HOST = "localhost";

	public static void main(String[] args) {
		try (Socket socket = new Socket(SERVER_HOST, ServerSample.SERVER_PORT);
				//サーバと通信するためのクライアント側の入出力Stream
				InputStream is = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(is));) {
			String line = in.readLine();
			System.out.println(line);
			
			System.out.println("サーバへ送信する");
			String message = new Scanner(System.in).nextLine();
			
			out.write((message + "\r\n").getBytes());
			out.flush();
			
			while(true) {
				line = in.readLine();
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
