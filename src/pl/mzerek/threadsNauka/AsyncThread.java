package pl.mzerek.threadsNauka;

public class AsyncThread extends Thread {
	
	public void run(){
		System.out.println("Starting the thread: " + getName());
		for (int i = 0; i < 3; i++) {
			System.out.println("In thread " + getName() + "; iteration " + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		AsyncThread asyncThread1 = new AsyncThread();
		AsyncThread asyncThread2 = new AsyncThread();
		
		asyncThread1.start();
		asyncThread2.start();

	}

}
