package main.chap8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise {

	public static void main(String[] args) {
		//main.chap8.Exercise.javaを取得
		Logger logger = LoggerFactory.getLogger("main.chap8.Exercise.java");
		
		logger.trace("トレースレベルログ");
		logger.debug("デバッグレベルログ");
		logger.info("情報レベルログ");
		logger.warn("警告レベルログ");
		logger.error("エラーレベルログ");
	}

}
