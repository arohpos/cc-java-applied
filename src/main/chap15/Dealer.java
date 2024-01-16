package main.chap15;

import java.io.Serializable;
import java.util.Random;

//今回のカードデッキはスート2種、各13枚、ジョーカーなしの固定でOK（ここの拡張性は不要）
public class Dealer implements Serializable{
	
	private static final long serialVersionUID = 1438341639893665635L;

	private Card[][] cards;
	
	//Singleton用インスタンス生成処理
	private static Dealer dealer = new Dealer();
	
	//Singleton用インスタンス呼び出しメソッド(staticのためクラス名から呼び出し可能)
	public static Dealer getInstance() {
		return  dealer;
	}
	
	//Singleton用privateコンストラクタ
	private Dealer() {
		this.cards = new Card[2][13];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 13; j++) {
				this.cards[i][j] = new Card(Suit.values()[i], j + 1);
			}
		}
		System.out.println("---カードデッキを準備しました。---");
	}
	
	//カードデッキのシャッフルメソッド
	public void shuffle() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 13; j++) {
				this.cards[i][j].setDealt(false);
			}
		}
		System.out.println("---デッキをシャッフルしました。---");
	}
	
	//ランダムな未使用のカードを一枚配るメソッド
	public Card deal() throws AllCardsAlreadyDealtException{
		Random random = new Random();
		int rand;
		
		if(this.countNotDealtCard() == 0) {
			throw new AllCardsAlreadyDealtException("すべてのカードはすでに配られています。");
		}else {			
			while(true) {
				rand = random.nextInt(26);
				if(this.cards[rand / 13][rand % 13].isDealt() == false) {
					this.cards[rand / 13][rand % 13].setDealt(true);
					break;
				}
			}
			return this.cards[rand / 13][rand % 13];
		}
		
	}
	
	//未配布カード枚数のカウントメソッド
	private int countNotDealtCard() {
		int notDealtCardCounter = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 13; j++) {
				if(this.cards[i][j].isDealt() == false) {
					notDealtCardCounter = notDealtCardCounter + 1;
				}
			}
		}
		return notDealtCardCounter;
	}
	
	
}
