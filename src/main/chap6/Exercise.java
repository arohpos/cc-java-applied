package main.chap6;

import java.util.ArrayList;

public class Exercise {
	public static String text =
			   "Alice was beginning to get very tired of sitting by "
			   + "her sister on the bank, and of having nothing to do: once or "
			   + "twice she had peeped into the book her sister was reading, but "
			   + "it had no pictures or conversations in it, `and what is the use "
			   + "of a book,\' thought Alice `without pictures or conversation?\'\n"
			   + "So she was considering in her own mind (as well as she could, "
			   + "for the hot day made her feel very sleepy and stupid), whether "
			   + "the pleasure of making a daisy-chain would be worth the trouble "
			   + "of getting up and picking the daisies, when suddenly a White "
			   + "Rabbit with pink eyes ran close by her.\nThere was nothing so "
			   + "very remarkable in that; nor did Alice think it so very much "
			   + "out of the way to hear the Rabbit say to itself, `Oh dear! "
			   + "Oh dear! I shall be late!\' (when she thought it over afterwards, "
			   + "it occurred to her that she ought to have wondered at this, but "
			   + "at the time it all seemed quite natural); but when the Rabbit "
			   + "actually took a watch out of its waistcoat-pocket, and looked "
			   + "at it, and then hurried on, Alice started to her feet, for it "
			   + "flashed across her mind that she had never before seen a rabbit "
			   + "with either a waistcoat-pocket, or a watch to take out of it, "
			   + "and burning with curiosity, she ran across the field after it, "
			   + "and fortunately was just in time to see it pop down a large "
			   + "rabbit-hole under the hedge.";  
	
	public static void main(String[] args) {

	//1)textをArrayListにいれる。
		//1-1)指定文字1文字以上が繰り返される場合は、別単語としてsplitする。
		 String[] splitedWords = text.split("[ ,.`'();:!?\n]+");
		 
		 
		 //1-2)表記を小文字に変換して、リストに格納する。
		 ArrayList<String> lowerCaseSplitedWordsList = new ArrayList<>();
		 for(String splitWord : splitedWords) {
			 lowerCaseSplitedWordsList.add(splitWord.toLowerCase());
		 }
		 
	//2)Streamの生成と加工、出力※Streamは加工毎に生成し直す必要がある。
		 //2-1)10文字以上の単語数をcount（戻り値がlongであることに注意）
		 long wordsCounter = lowerCaseSplitedWordsList.stream()
		 	.filter(lowerCaseSplitedWord -> lowerCaseSplitedWord.length() >= 10)
		 	.count();
		 
		 System.out.println("10文字以上の単語は" + wordsCounter + "個ありました。");
		 
		 //2-2)10文字以上の単語をまとめて出力する。
		 lowerCaseSplitedWordsList.stream()
		 	.filter(lowerCaseSplitedWord -> lowerCaseSplitedWord.length() >= 10)
		 	.forEach(lowerCaseSplitedWord -> System.out.println("[" + lowerCaseSplitedWord + "]:文字数" + lowerCaseSplitedWord.length()));
		 		 	
	}

}
