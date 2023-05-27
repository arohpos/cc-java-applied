package main.chap8;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise {

	public static void main(String[] args) {
		
		String level;
		System.out.println("ログの出力レベルを入力してください。");
		System.out.println("[trace, debug, info, warn, error]");
		
		Scanner scanner = new Scanner(System.in);
		
		level = scanner.next();
		Logger logger = LoggerFactory.getLogger(level);
		
		logger.trace("トレースレベルログ");
		logger.debug("デバッグレベルログ");
		logger.info("情報レベルログ");
		logger.warn("警告レベルログ");
		logger.error("エラーレベルログ");
		
		scanner.close();
	}

}
