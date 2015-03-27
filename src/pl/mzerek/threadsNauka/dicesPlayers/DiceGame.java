package pl.mzerek.threadsNauka.dicesPlayers;

import java.util.Random;

class Gamers {
	private Gamers() {
	}

	public static final String JOE = "Joe";
	public static final String JANE = "Jane";
}

class Dice {
	private static String turn;

	synchronized public static String getTurn() {
		return turn;
	}

	synchronized public static void setTurn(String player) {
		turn = player;
	}

	public static void setWhoStarts(String name) {
		turn = name;
	}

	private Dice() {
	}

	private static Random random = new Random();

	public static int roll() {
		// random.nextInt(6) gives values from 0 to 5, so add 1 to result in
		// roll()
		return random.nextInt(6) + 1;
	}

}

class Player extends Thread {

	private String currentPlayer;
	private String otherPlayer;

	public Player(String thisPlayer) {
		currentPlayer = thisPlayer;
		otherPlayer = thisPlayer.equals(Gamers.JOE) ? Gamers.JANE : Gamers.JOE;
	}

	public void run() {
		for (int i = 0; i < 6; i++) {
			synchronized (Dice.class) {
			
				while (!Dice.getTurn().equals(currentPlayer)) {
					try {
						Dice.class.wait(1000);
						System.out.println(currentPlayer + " was waiting for "
								+ otherPlayer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

				System.out.println(Dice.getTurn() + " throws " + Dice.roll());
				// set the turn to otherPlayer, and notify the otherPlayer
				Dice.setTurn(otherPlayer);
				Dice.class.notifyAll();

			}
		}

	}

}

class DiceGame {
	public static void main(String[] s) {
		Player player1 = new Player(Gamers.JANE);
		Player player2 = new Player(Gamers.JOE);
		// don't forget to set who starts the game
		Dice.setWhoStarts(Gamers.JOE);
		player1.start();
		player2.start();
	}
}
