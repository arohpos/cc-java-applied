package main.chap11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class URLwithProxySample {
	private static final String PROXY_HOST = "XXX.XXX.XXX.XXX";
	private static final int PROXY_PORT = 8080;
	

	public static void main(String[] args) {
		
		try {
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST,PROXY_PORT));
			URL url = new URL("https://codecamp.jp/");
			
			URLConnection connection = url.openConnection(proxy);
			
			try {
				InputStream is = connection.getInputStream();
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
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
