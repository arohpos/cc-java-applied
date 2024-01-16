package main.chap14;

public class Calc {
	public int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public void add(int value) {
		synchronized (this) {//これがあることで排他処理が出来、Thread-safeな仕様となる。			
			total = total + value;
		}
	}
	

}
