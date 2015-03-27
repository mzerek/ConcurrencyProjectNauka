package pl.mzerek.java7ConcurrencyBook.chapter1.ex2;

public class PrimeGeneratorDemo {

	public static void main(String[] args) {
		Thread task = new PrimeGenerator();		
		task.start();
		try {
			Thread.sleep(500);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}

}
