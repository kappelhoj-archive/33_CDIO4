package test.controller;

import controller.LandOnFieldController;
import controller.PrisonController;
import controller.PropertyController;
import entity.DiceCup;
import entity.Player;

/**
 * MainControler class controls all the other controllers and is responsible for
 * moving the players around the board.
 *
 */
public class MainControllerTestStub {
	private Player[] players;
	private DiceCup dice;
	private PrisonControllerTestStub prisonController;
	private FieldControllerTestStub fieldController;
	private PropertyController propertyController;

	private int turn;
	private int numExtraTurn;
	private boolean extraTurn;
	private boolean testExtraTurn = false;

	/**
	 * Constructor: Creates the needed variables for the main controller and
	 * construct new objects of some of the other classes.
	 */
	public MainControllerTestStub() {
		turn = (int) (Math.random() * players.length);

		numExtraTurn = 0;
		// Construct a dicecup
		dice = new DiceCup();
		// Construct a prison controller.
		prisonController = new PrisonControllerTestStub(this);

		// Construct a field controller
		fieldController = new FieldControllerTestStub(prisonController, this);
		propertyController = new PropertyController();
	}

	/**
	 * Method main: This methods starts the program.
	 */
	public static void main(String[] args) {
		// Creates the main controller.
		MainControllerTestStub game = new MainControllerTestStub();
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
		} while (players[turn].getHasLost());
	}

	/**
	 * Method playTurn: Plays one dice roll of a player, and do everything
	 * appropiate depending on where he lands.
	 */
	public void playTurn() {
		// Check if the player is in prison. If he is, hand over control of the
		// turn to the prison.
		if (prisonController.checkInPrison(players[turn])) {

			if (!prisonController.inPrison(players[turn]))
				return;
		}

		// Roll the dice.
		int diceSum = rollDice();
		// Check if he rolled the same on both dice.
		checkForExtraTurn();
		// If this is the third time he rolled the double something. Send him to
		// prison.
		if (numExtraTurn == 3) {
			prisonController.sendToPrison(players[turn]);
			extraTurn = false;
			return;
		}
		// Move the player depending on the dice roll
		movePlayer(diceSum);

		// add landOnField methods.
		fieldController.landOnField(players[turn], diceSum);

		// Ask the player what he wants to do now.
		//playerTurnDecision();
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
	}

	/**
	 * Method rollDice: Roll the dice and return their sum.
	 * 
	 * @return Sum of the dice.
	 */
	public int rollDice() {
		// Roll the dice.
		dice.shakeCup();

		return dice.getDiceValue()[0] + dice.getDiceValue()[1];

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
		String[] output;
		if (input == null) {
			output = new String[1];
		} else {
			output = new String[input.length + 1];
			for (int i = 0; i < input.length; i++) {
				output[i] = input[i];
			}
		}

		output[output.length - 1] = "Gå tilbage";
		return output;
	}

}
