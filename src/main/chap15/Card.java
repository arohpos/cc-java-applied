package main.chap15;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable{

	private static final long serialVersionUID = 6260998554002282581L;
	private Suit suit;
	private int number;
	private boolean isDealt;
	
	//コンストラクタ
	protected Card(Suit suit, int number) {
		this.suit = suit;
		this.number = number;
		this.isDealt = false;
	}
	
	@Override
	public String toString() {
		return this.suit.getSuitName() + this.number;
	}
	
	@Override
	//等価の比較（Stringとintでの等価比較方法の差に注意）
	public boolean equals(Object obj) {
		if(obj instanceof Card) {
			Card anotherCard = (Card)obj;
			if(this.number == anotherCard.number) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	@Override
	//comparer＞anotherCardの場合、正の数が出力される。
	public int compareTo(Card anotherCard) {
		if(this.number > anotherCard.number) {
			return 1;
		}else if(this.number < anotherCard.number) {
			return -1;
		}else{
			return 0;
		}
	}
	
	
	//---------------------------------------------------------------------------------------
	protected Suit getSuit() {
		return suit;
	}
	protected void setSuit(Suit suit) {
		this.suit = suit;
	}

	protected int getNumber() {
		return number;
	}
	protected void setNumber(int number) {
		this.number = number;
	}

	protected boolean isDealt() {
		return isDealt;
	}

	protected void setDealt(boolean isDealt) {
		this.isDealt = isDealt;
	}
	
	
	
}
