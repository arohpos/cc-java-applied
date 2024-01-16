package main.chap14;

public class ProcassAdd implements Runnable{
	private Calc calc;
	int number;
	int times;
	
	public ProcassAdd(Calc calc, int number, int times) {
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
