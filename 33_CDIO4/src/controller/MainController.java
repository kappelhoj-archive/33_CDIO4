package controller;

import desktop_resources.GUI;
import entity.DiceCup;
import entity.GameBoard;
import entity.Player;
import test.TestModeController;

/**
 * MainControler class controls all the other controllers and is responsible for
 * moving the players around the board.
 *
 */
public class MainController {
	private Player[] players;
	private DiceCup dice;
	private PrisonController prisonController;
	// private GameBoard board;
	private LandOnFieldController fieldController;

	private TestModeController testMode;

	private int turn;
	private int numExtraTurn;
	private boolean extraTurn;
	private boolean testExtraTurn = false;

	/**
	 * Constructor: Creates the needed variables for the main controller and
	 * construct new objects of some of the other classes.
	 */
	MainController(String[] args) {
		// Used for testmode only.
		if (args != null)
			testMode = new TestModeController(args[0]);

		// Creates a board that can store all the fields.
		// board = new GameBoard();
		// Creates an objet that iniliazie the GUI gameboard.
		GUICreator createGUI = new GUICreator();
		// Initialise the class that reads fields from a text file.

		// Ask the players their names on the GUI
		String[] playerNames = createGUI.getPlayerNames();

		// Create an array of players, and add their names.
		this.players = new Player[playerNames.length];
		for (int i = 0; i < players.length; i++) {
			this.players[i] = new Player(playerNames[i]);
		}

		numExtraTurn = 0;
		// Construct a dicecup
		dice = new DiceCup();
		// Construct a prison controller.
		prisonController = new PrisonController(this);

		// Construct a field controller
		fieldController = new LandOnFieldController(prisonController, this);
	}

	/**
	 * Method main: This methods starts the program.
	 */
	public static void main(String[] args) {
		// Creates the main controller.
		MainController game = new MainController(args);
		// Starts the game
		game.playGame();
	}

	/**
	 * Method changeTurn: Change the turn so that it is the next players turn.
	 * Ignores players that have lost.
	 */
	public void changeTurn() {
		// Chance the players turn untill someone has lost.
		do {
			turn = (turn + 1) % players.length;
		} while (players[turn].getLost());
	}

	/**
	 * Method playTurn: Plays one dice roll of a player, and do everything
	 * appropiate depending on where he lands.
	 */
	public void playTurn() {
		// Check if the player is in prison. If he is, hand over control of the
		// turn to the prison.
		if (prisonController.checkIfInPrison(players[turn])) {

			if (!prisonController.inPrison(players[turn]))
				return;
		}
		// Tell the player it is his turn on the GUI.
		GUI.getUserButtonPressed(players[turn].getName() + " det er din tur.", "Slå med terninger");

		// Roll the dice.
		int diceSum = rollDice();
		// Check if he rolled the same on both dice.
		checkForExtraTurn();
		// If this is the third time he rolled the double something. Send him to
		// prison.
		if (numExtraTurn == 3) {
			prisonController.sentToPrison(players[turn]);
			extraTurn = false;
			return;
		}
		// Move the player depending on the dice roll
		movePlayer(diceSum);

		// add landOnField methods.
		fieldController.landOnField(players[turn], diceSum);

		// Ask the player what he wants to do now.
		playerTurnDecision();
	}

	/**
	 * Method playGame: Plays the game until someone has won.
	 */
	public void playGame() {
		// Keep changing turn until someone has won.
		while (true) {
			changeTurn();
			do {
				playTurn();
			} while (extraTurn);

		}
	}

	/**
	 * Method checkforExtraTurn: Checks if the player rolled the same on both
	 * dice. Returns true if that was the case.
	 * 
	 * @return boolean
	 */
	public boolean checkForExtraTurn() {
		// Check if the dice have equals value, if it does give it an extra
		// turn.
		// testExtraTurn Er kune relevant når testmode er aktiv. Det gør
		// at man kan give spilleren en ekstra tur. Uanset hvad han slår.
		if (dice.getDiceValue()[0] == dice.getDiceValue()[1] || testExtraTurn) {
			extraTurn = true;
			numExtraTurn++;
		}
		// If not end the turn.
		else {
			extraTurn = false;
			numExtraTurn = 0;
		}

		// Testing stuff:
		testExtraTurn = false;
		return extraTurn;
	}

	/**
	 * Method givePlayer4000: Gives the player 4000. Should be used when they
	 * pass the start field.
	 */
	private void givePlayer4000() {
		players[turn].changeAccountBalance(4000);
		GUI.setBalance(players[turn].getName(), players[turn].getAccountBalance());
	}

	/**
	 * Method movePlayerOnGui: Moves the players car to a position.
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

	/**
	 * Method rollDice: Roll the dice and return their sum.
	 * 
	 * @return Sum of the dice.
	 */
	public int rollDice() {
		// Roll the dice.
		dice.shakeCup();
		// Set the dice on the GUI
		GUI.setDice(dice.getDiceValue()[0], dice.getDiceValue()[1]);

		// Only used if testing is active.
		if (testMode.isActive()) {
			int newRoll = testMode.options(players[turn], this, fieldController);
			if (newRoll >= 0)
				return newRoll;
		}

		return dice.getDiceValue()[0] + dice.getDiceValue()[1];

	}

	/**
	 * Method removePlayerOnGUI: Remove all cars of a player.
	 * 
	 * @param playerName
	 *            Name of the player.
	 */
	public void removePlayerOnGUI(String playerName) {
		// Remove all the cars of the player
		GUI.removeAllCars(playerName);
	}

	/**
	 * Method movePlayer: Moves the player forward on the board.
	 * 
	 * @param diceSum
	 *            The sum of the dice.
	 */
	public void movePlayer(int diceSum) {

		// Check if the player can move to the next field. If not move him to 1
		// and continue moving forward.
		if (players[turn].getPosition() + diceSum <= 40) {
			players[turn].setPosition(players[turn].getPosition() + diceSum);
		} else {
			givePlayer4000();
			int difference = 40 - players[turn].getPosition();
			players[turn].setPosition(diceSum - difference);
		}
		// Moves the player to his new position on the GUI.
		movePlayerOnGUI();
	}

	/**
	 * Method playerTurnDecision: Gives the player some options of what he wants
	 * to do.
	 */
	public void playerTurnDecision() {
		// Boolean that holds the decision of if the player want to end his
		// turn.
		boolean endTurn = false;

		String output = "Hvad vil du foretage dig?";
		final String END_YOUR_TURN = "Slut din tur.";

		// Keep asking for what the player wants to do until he chooses to add
		// the player.
		String userSelection;
		while (!endTurn) {
			userSelection = GUI.getUserSelection(output, END_YOUR_TURN);
			switch (userSelection) {
			case END_YOUR_TURN:
				endTurn = true;
				break;
			}
		}

	}

	public void TESTsetExtraTurn(boolean input) {
		testExtraTurn = true;
	}

	/**
	 * Static Method addReturnArray: Add the String "Gå tilbage" at the end of a
	 * String[]. Can be used for menus.
	 * 
	 * @param input
	 *            The Array input.
	 * @return The array with "Gå tilbage" at the end.
	 */
	public static String[] addReturnToArray(String[] input) {
		String[] output = new String[input.length + 1];
		for (int i = 0; i < input.length; i++) {
			output[i] = input[i];
		}

		output[output.length - 1] = "Gå tilbage";
		return output;
	}

}
