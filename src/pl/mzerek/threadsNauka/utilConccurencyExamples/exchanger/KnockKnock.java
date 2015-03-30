package pl.mzerek.threadsNauka.utilConccurencyExamples.exchanger;

import java.util.concurrent.Exchanger;

public class KnockKnock {

	public static void main(String[] args) {
		Exchanger<Dog> sillyTalk = new Exchanger<Dog>();
		new DukeThread(sillyTalk).start();
		new CoffeeShopThread(sillyTalk).start();
		
		

	}

}


class DukeThread extends Thread{
	private Exchanger<Dog> sillyTalk;
	
	public DukeThread(Exchanger<Dog> args){
		this.sillyTalk = args;
	}
	
	public void run(){
		
		Dog replay = null;
		try{
			Dog dog= new Dog();
			dog.setName("DukeAzor");
			
			replay = sillyTalk.exchange(dog);
			System.out.println("CofeeShop: " + replay.getName());
			
			
			dog.setName("DukeBunia");
			replay = sillyTalk.exchange(dog);
			System.out.println("CofeeShop: " + replay.getName());
			
			dog.setName("DukeCygan");
			replay = sillyTalk.exchange(dog);
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}		
	}	
}

class CoffeeShopThread extends Thread{
	private Exchanger<Dog> sillyTalk;
	
	public CoffeeShopThread(Exchanger<Dog> args){
		this.sillyTalk = args;
	}
	
	public void run(){
		Dog replay = null;	
		
		try{
			Dog dog= new Dog();
			dog.setName("CoffeAzor");
			replay = sillyTalk.exchange(dog);
			System.out.println("Duke: " + replay.getName());
			
			dog.setName("CoffeBunia");
			replay = sillyTalk.exchange(dog);
             // print what Duke said
             System.out.println("Duke: " + replay.getName());
			
             dog.setName("CoffeCygan");
 			replay = sillyTalk.exchange(dog);
			System.out.println("Duke: " + replay.getName());
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}

class Dog {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
