package main.chap14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSample {
	
	static class ProcessAdd implements Runnable{
		private Calc calc;
		int number;
		int times;
		
		public ProcessAdd(Calc calc, int number, int times) {
			this.calc = calc;
			this.number = number;
			this.times = times;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < times; i++) {
				calc.add(number);
			}		
		}
	}
	
	private static final int NUM_THREADS = 1000;
	private static final int NUM_ADD = 10;	
	private static final int NUM_TIMES = 100;
	
	public static void main(String[] args) {
		Calc calc = new Calc();
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		for(int i = 0; i < NUM_THREADS; i++) {
			executor.execute(new ProcessAdd(calc, NUM_ADD, NUM_TIMES));
		}
		executor.shutdown();
		
		System.out.printf("合計は%dです。", calc.getTotal());		
	}
}
