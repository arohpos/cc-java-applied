package main.chap8;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main_8_3_2 {
	
	private static Log logger = LogFactory.getLog(Main_8_3_2.class);

	public static void main(String[] args) {
	    logger.info("起動開始");
	    logger.info("サーバーとの接続を開始");
	    logger.info("Enterキーが押された");
	    logger.debug("Hoge#toString() -> hoge=aaa, fuga=fugafuga");
	    logger.error("hoge.fuga.HogehogeException timeout");
	    logger.error("サーバーの接続に失敗");
	    logger.info("起動完了");
	    logger.info("画面表示完了");
	    logger.trace("if文の中に入った");
	    logger.info("ボタンが押された");
	}

}
