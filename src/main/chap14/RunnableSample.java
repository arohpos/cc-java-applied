package main.chap14;

public class RunnableSample implements Runnable{
	
	private static final int NUM_THREADS = 10;
	private static final int NUM_TIMES = 10;
	
	@Override
	//スレッドで実行する処理
	public void run() {
		for(int i = 0; i < NUM_TIMES; i++) {
			System.out.printf("%s : %d\n", Thread.currentThread().getName(), i);
		}
	}

	public static void main(String[] args) throws InterruptedException{
		Thread[] threads = new Thread[NUM_THREADS];
		
		//スレッド作成
		for(int i = 0;i<threads.length; i++) {
			threads[i] = new ThreadSample();
		}
		
		//スレッド開始
		for(Thread thread: threads) {
			thread.start();//runメソッドは呼び出さない。
		}
		//スレッド終了
		for(Thread thread: threads) {
			thread.join();
		}

	}

}
