package controller;

import entity.DiceCup;
import entity.Player;


/**
 * MainControler class controls all the other controllers and is responsible for
 * moving the players around the board.
 * 
 * @author Arvid,
 */
public class MainController {
	private Player[] players;
	private DiceCup dice;


	private int turn;
	private int numExtraTurn;
	private boolean extraTurn;

	/**
	 * Creates the 
	 */
	private MainController() {
		PlayerCreatorGUI createPlayers =new PlayerCreatorGUI();
		String[] playerNames=createPlayers.getPlayerNames();
		this.players = new Player[playerNames.length];

		for (int i = 0; i < players.length; i++) {
			this.players[i] = new Player(playerNames[i]);
		}
		
		numExtraTurn = 0;
		dice = new DiceCup();
	}

	public static void main(String[] args) {
		MainController game = new MainController();
		game.playGame();
	}

	public void changeTurn() {
		do {
			turn = (turn + 1) % players.length;
		} while (players[turn].getPlayerHasLost());
	}

	public void playTurn() {
		// Roll the dice
		dice.shakeCup();
		int sum = dice.getDiceValue()[0] + dice.getDiceValue()[1];
		checkForExtraTurn();

		// Move the player to his position and if he passes field 40 move him to
		// 1 and continue counting.
		if (players[turn].getPosition() + sum <= 40) {
			players[turn].setPosition(players[turn].getPosition() + sum);
		} else {
			givePlayer4000();
			int difference = 40 - players[turn].getPosition();
			players[turn].setPosition(sum - difference);
		}
	}

	public void playGame() {
		while (true) {
			changeTurn();
			do {
				playTurn();
			} while (extraTurn);

		}
	}

	private void checkForExtraTurn() {
		if (dice.getDiceValue()[0] == dice.getDiceValue()[1]) {
			extraTurn = true;
			numExtraTurn++;
		} else {
			extraTurn = false;
			numExtraTurn = 0;
		}
	}
	
	private void givePlayer4000(){
		players[turn].changeAccountBalance(4000);
	}

}
