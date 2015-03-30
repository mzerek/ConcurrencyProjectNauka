package pl.mzerek.threadsNauka.utilConccurencyExamples.semaphore;

import java.util.concurrent.Semaphore;

public class ATMRoom {

	public static void main(String[] args) {
		Semaphore machines = new Semaphore(2);
		
		// list of people waiting to access the machine
		new Person(machines, "Adam");
		new Person(machines, "Bolek");
		new Person(machines, "Cela");
		new Person(machines, "Damian");
		new Person(machines, "Edek");
	}

}

class Person extends Thread {
	private Semaphore machines;
	


	public Person(Semaphore machines, String name) {
		this.machines = machines;
		this.setName(name);
		this.start();
	}

	public void run() {
		try {
			System.out.println( getName() + " waiting to access an ATM machine");
			machines.acquire();
			System.out.println( getName() + " is accessing an ATM machine");
		
			Thread.sleep(1000); // simulate the time required for withdrawing
								// amount
			System.out.println( getName() + " is done using the ATM machine");
			machines.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
