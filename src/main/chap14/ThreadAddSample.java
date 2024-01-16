package main.chap14;

public class ThreadAddSample {
	private static final int NUM_THREADS = 1000;
	private static final int NUM_ADD = 10;	
	private static final int NUM_TIMES = 100;

	public static void main(String[] args) throws InterruptedException{
		Calc calc = new Calc();
		Thread[] threads = new Thread[NUM_THREADS];
		
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new ProcassAdd(calc, NUM_ADD, NUM_TIMES));
		}
		
		for(Thread thread: threads) {
			thread.start();
		}
		
		for(Thread thread: threads) {
			thread.join();
		}
		
		System.out.printf("合計は%dです。", calc.getTotal());
	}

}
