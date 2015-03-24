package pl.mzerek.threadsNauka;



class CounterDataRace {
	public static long count = 0;
	
}

class UseCounter implements Runnable {
	
	
	//added synchronized to fixed it
	public synchronized void increment(){
		CounterDataRace.count++;
		System.out.println(CounterDataRace.count + " " + Thread.currentThread().getName());	
	}	
	
	public void run(){
		increment();
		increment();
		increment();
	}
	
}


public class DataRace {

	public static void main(String[] args) {		
		UseCounter c = new UseCounter();
		
		Thread t1 = new Thread(c, "t1");
		Thread t2 = new Thread(c, "t2");
		Thread t3 = new Thread(c, "t3");
		
		t1.start();
		t2.start();
		t3.start();

	}

}




