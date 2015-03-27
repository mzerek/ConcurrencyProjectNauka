package pl.mzerek.threadsNauka.coffeeRestaurant;

public class CoffeeShop {

	public static void main(String[] args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine();
		Waiter waiter = new Waiter();
		coffeeMachine.start();
		waiter.start();

	}

}
