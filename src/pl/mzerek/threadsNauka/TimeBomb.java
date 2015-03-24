package pl.mzerek.threadsNauka;

public class TimeBomb extends Thread {
	
	String[] timeStr= {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

	
	public void run(){
		
		for(int i = timeStr.length-1; i >=0; i--) {
			
			try {
				System.out.println(timeStr[i]);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	public static void main(String[] args) {
		Thread timer = new TimeBomb();
		System.out.println("Startin count down");
		timer.start();
		try {
			timer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		System.out.println("Boom!");
		
	}

}
