package controller;

import desktop_resources.GUI;
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
	private PrisonController prisonController;

	private int turn;
	private int numExtraTurn;
	private boolean extraTurn;

	/**
	 * Creates the 
	 */
	MainController() {
		GUICreator createPlayers =new GUICreator();
		String[] playerNames=createPlayers.getPlayerNames();
		this.players = new Player[playerNames.length];

		for (int i = 0; i < players.length; i++) {
			this.players[i] = new Player(playerNames[i]);
		}
		
		numExtraTurn = 0;
		dice = new DiceCup();
		prisonController = new PrisonController(this);
	}

	public static void main(String[] args) {
		MainController game = new MainController();
		game.playGame();
	}

	public void changeTurn() {
		do {
			turn = (turn + 1) % players.length;
		} while (players[turn].getLost());
	}
	

	public void playTurn() {
		
		if(prisonController.checkIfInPrison(players[turn]))
		{
			prisonController.inPrison(players[turn]);
			return;
		}
		
		int diceSum = rollDice();
		checkForExtraTurn();
		if(numExtraTurn == 3)
		{
			prisonController.sentToPrison(players[turn]);
			extraTurn = false;
			return;
		}
		movePlayer(diceSum);
		//landOnField
		playerTurnDecision();
	}

	public void playGame() {
		while (true) {
			changeTurn();
			do {
				playTurn();
			} while (extraTurn);

		}
	}

	public boolean checkForExtraTurn() {
		if (dice.getDiceValue()[0] == dice.getDiceValue()[1]) {
			extraTurn = true;
			numExtraTurn++;
		} else {
			extraTurn = false;
			numExtraTurn = 0;
		}
		return extraTurn;
	}
	
	private void givePlayer4000(){
		players[turn].changeAccountBalance(4000);
	}
	
	
	/**
	 * Moves the players car to a position.
	 * 
	 * @param playerName
	 *            Name of the player to be moved.
	 * @param position
	 *            New position of the player.
	 */
	public void movePlayerOnGUI() {
		// Remove all the cars of the player
		GUI.removeAllCars(players[turn].getName());
		// Place a new car on the new position.
		GUI.setCar(players[turn].getPosition(), players[turn].getName());
	}
	
	public int rollDice(){
		dice.shakeCup();
		GUI.setDice(dice.getDiceValue()[0], dice.getDiceValue()[1]);
		return dice.getDiceValue()[0] + dice.getDiceValue()[1];
	}

	/**
	 * Remove all cars of a player.
	 * 
	 * @param playerName
	 *            Name of the player.
	 */
	public void removePlayerOnGUI(String playerName) {
		// Remove all the cars of the player
		GUI.removeAllCars(playerName);
	}
	
	public void movePlayer(int diceSum)
	{

		// Move the player to his position and if he passes field 40 move him to
		// 1 and continue counting.
		if (players[turn].getPosition() + diceSum <= 40) {
			players[turn].setPosition(players[turn].getPosition() + diceSum);
		} else {
			givePlayer4000();
			int difference = 40 - players[turn].getPosition();
			players[turn].setPosition(diceSum - difference);
		}
		movePlayerOnGUI();
	}

	public void playerTurnDecision()
	{
		boolean endTurn = false;
		String output = "Hvad vil du foretage dig?";
		final String ENDYOURTURN = "Slut din tur.";
		String userSelection = GUI.getUserSelection(output, ENDYOURTURN);
		while(!endTurn)
		{
			switch(userSelection){
				case ENDYOURTURN: endTurn = true;
				break;
			}
		}
	}
}
