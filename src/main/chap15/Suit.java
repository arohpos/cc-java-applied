package main.chap15;

//クラスではなくenumであり、newはできないものの、外部から呼び出せる。
public enum Suit {
	//!:以下はSuit.SPADEでインスタンスとして呼び出せる。ordinal()の値がcompareToされるため、弱い順に並べる。
	//!:Suitはコンストラクタを利用し、この時点ですでにインスタンス化されている。実質staticなので値が固定となる。
	DIAMOND("ダイヤ"),
	SPADE("スペード");
	
	//フィールド
	private String suitName;
	
	//コンストラクタ
	private Suit(String suitName) {
		this.suitName = suitName;
	}

	protected String getSuitName() {
		return suitName;
	}
	protected void setSuitName(String suitName) {
		this.suitName = suitName;
	}
}
