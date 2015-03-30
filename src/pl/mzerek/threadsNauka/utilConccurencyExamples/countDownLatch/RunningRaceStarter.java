package pl.mzerek.threadsNauka.utilConccurencyExamples.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class RunningRaceStarter {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(10);
		// count from 5 to 0 and then start the race
		
		
		// instantiate three runner threads
        new Runner(counter, "Alek");
        new Runner(counter, "Bolek");
        new Runner(counter, "Cela");
        
        System.out.println("Starting the countdown ");
        long countVal = counter.getCount();
        while(countVal > 0 ){
        	Thread.sleep(1000); //mean 1 sek.
        	System.out.println(countVal);
        	if(countVal == 1){
        		System.out.println("Start");
        	}
        	counter.countDown(); 
        	countVal = counter.getCount();
        }		
	}

}

class Runner extends Thread {

	private CountDownLatch timer;

	public Runner(CountDownLatch cdl, String name) {
		this.timer = cdl;
		this.setName(name);
		System.out.println(this.getName()
				+ " ready and waiting for the count down to start");
		this.start();
	}

	public void run() {
		try {			
			// wait for the timer count down to reach 0
			timer.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " started running");

	}

}