package pl.mzerek.threadsNauka;

// example from the Cricket game

class Balls {
	public static long balls = 0;
}

class Runs {
	public static long runs = 0;
}

class Counter implements Runnable {

	public void incrementBallAfterRun() {
		synchronized (Runs.class) {
			synchronized (Balls.class) {
				System.out.println("in incrementBallAfterRun...");
				Runs.runs++;
				Balls.balls++;
			}
		}

	}

	public void incrementBallAfterBall() {
		synchronized (Balls.class) {
			synchronized (Runs.class) {
				System.out.println("in incrementBallAfterBall...");
				Balls.balls++;
				Runs.runs++;

			}
		}

	}

	@Override
	public void run() {
		incrementBallAfterRun();
		incrementBallAfterBall();

	}

}

public class DeadLock {

	public static void main(String[] args) throws InterruptedException {
		Counter c = new Counter();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		
		t1.start();
		t2.start();
		
		System.out.println("Waiting for threads to complete execution...");
		
		t1.join();
		t2.join();
		
		System.out.println("Done");

	}

}
