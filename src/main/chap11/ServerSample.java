package main.chap11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSample {

	public static final int SERVER_PORT = 9999;

	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);) {
			InetAddress server = InetAddress.getLocalHost();
			System.out.printf("%s: Server start port[%d]...\r\n\r\n", server.getHostAddress(), SERVER_PORT);

			LOOP: while (true) {
				//クライアントからの接続をトリガーにクライアントに接続するためのソケットを作成する。
				try (Socket socket = serverSocket.accept()) {
					InetAddress client = socket.getInetAddress();
					//クライアントと通信するためのサーバ側の入出力Stream
					try (InputStream is = socket.getInputStream();
							OutputStream os = socket.getOutputStream();
							BufferedReader in = new BufferedReader(new InputStreamReader(is));
							PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));) {
						out.printf("Welcom! from%s\r\n", server.getHostAddress());
						out.flush();

						String message = in.readLine();
						System.out.printf("%s: %s\r\n", client.getHostAddress(), message);

						out.printf("%s: Hi! %s\r\n", server.getHostAddress(), message);
						out.flush();

						message = message.toLowerCase();

						switch (message) {
						case "exit":
						case "quit":
							break LOOP;
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			System.out.printf("\r\n%s: Server stop.\r\n", server.getHostAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
