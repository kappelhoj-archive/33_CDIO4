package controller;

import desktop_resources.GUI;
import entity.DiceCup;
import entity.Player;
import test.TestModeController;

/**
 * This class is the main controller. It is responsible of handing out
 * assigments to the different controllers and moving the player.
 * 
 * @author Gruppe33
 *
 */
public class MainController {
	private Player[] players;
	private DiceCup dice;
	private PrisonController prisonController;
	private FieldController fieldController;
	private PropertyController propertyController;
	private BankController bankController;
	private TestModeController testMode;

	private int turn;
	private int numExtraTurn;
	private boolean extraTurn;
	private boolean testExtraTurn = false;

	/**
	 * Constructor: Creates the needed variables for the main controller and
	 * construct new objects of some of the other classes.
	 */
	public MainController(String[] args) {
		// Used for testmode only.
		if (args.length != 0) {
			if (args[0].toLowerCase().equals("testmode"))
				testMode = new TestModeController(true);
		} else {
			testMode = new TestModeController(false);
		}

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

		// Set a random player to start.
		turn = (int) (Math.random() * players.length);

		numExtraTurn = 0;

		dice = new DiceCup();

		bankController =new BankController(propertyController);
		prisonController = new PrisonController(this,bankController);
		propertyController = new PropertyController();
		
		fieldController = new FieldController(prisonController, this, propertyController,bankController);
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
		// Chance the players turn until someone has lost.
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
		// Tell the player it is his turn on the GUI. Say something different
		// depending on his last roll.
		if (numExtraTurn < 1)
			GUI.getUserButtonPressed(players[turn].getName() + " det er din tur.", "Slå med terninger");
		else if (numExtraTurn == 1) {
			GUI.getUserButtonPressed(players[turn].getName() + " det er din tur igen, fordi du har slået to ens ",
					"Slå med terninger");
		} else {
			GUI.getUserButtonPressed(
					players[turn].getName()
							+ " det er din tur igen, fordi du har slået to ens. Næste gang bliver du sendt i fængsel.",
					"Slå med terninger");
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
		playerTurnDecision();
	}

	/**
	 * Method playGame: Plays the game until someone has won.
	 */
	public void playGame() {
		GUI.getUserButtonPressed("En tilfældig spiller er valgt til at starte", "Start spil");
		// Keep changing turn until someone has won.
		while (true) {
			changeTurn();
			// If a winner is found delcare him winner and close the game.
			if (checkForWinner() != null) {
				GUI.getUserButtonPressed("Tillykke " + checkForWinner().getName() + " har vundet.", "Sweet");
				GUI.close();
				break;
			}

			do {
				playTurn();
			} while (extraTurn && !players[turn].getHasLost());

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
		// testExtraTurn is used by the testmode controller to force an extra
		// turn.
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
	public void givePlayer4000() {
		players[turn].changeAccountBalance(4000);
		GUI.setBalance(players[turn].getName(), players[turn].getAccountBalance());
		GUI.getUserButtonPressed("Du passerede start og modtager 4.000.", "Ok");
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
		dice.shake();
		// Set the dice on the GUI
		GUI.setDice(dice.getDiceValue()[0], 2, (int) (2 * (Math.random() - 0.5) + 7), dice.getDiceValue()[1], 3,
				(int) (2 * (Math.random() - 0.5) + 7));

		// If testmode is active open the testmode menu.
		if (testMode.isActive()) {
			int newRoll = testMode.options(players[turn], this, fieldController);
			// Change the diceroll if the player chose to.
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

		// Check if the player can move to the next field problem free.
		if (players[turn].getPosition() + diceSum <= 40 && players[turn].getPosition() + diceSum > 0) {
			players[turn].setPosition(players[turn].getPosition() + diceSum);
		}
		// If the dicesum is negative, move backwards.
		else if (players[turn].getPosition() + diceSum < 1) {
			players[turn].setPosition(40 + diceSum + players[turn].getPosition());
		}
		// If the player passes 40. Calculate his new position based on his
		// roll.
		else {
			int difference = 40 - players[turn].getPosition();
			players[turn].setPosition(diceSum - difference);
			movePlayerOnGUI();
			givePlayer4000();
		}
		// Moves the player to his new position on the GUI.
		movePlayerOnGUI();
	}

	/**
	 * Method movePlayerTo: Move the player to a specific location.
	 * 
	 * @param newPos
	 *            The new position of the player.
	 */
	public void movePlayerTo(int newPos) {
		int currentPos = players[turn].getPosition();
		players[turn].setPosition(newPos);
		if (newPos < currentPos) {
			givePlayer4000();
		}
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
		if (players[turn].getInPrison() || players[turn].getHasLost()) {
			return;
		}

		String output = "Hvad vil du foretage dig?";
		final String END_YOUR_TURN = "Slut din tur";
		final String HOUSES_AND_HOTELS = "Køb huse";

		// Keep asking for what the player wants to do until he chooses to add
		// the player.
		String userSelection;
		while (!endTurn) {
			userSelection = GUI.getUserSelection(output, END_YOUR_TURN, HOUSES_AND_HOTELS);
			switch (userSelection) {
			case END_YOUR_TURN:
				endTurn = true;
				break;
			case HOUSES_AND_HOTELS:
				propertyController.buyBuildingMenu(players[turn]);
				break;

			}
		}

	}

	/**
	 * Method TESTsetExtraTurn: This method is only for testing. But it makes
	 * sure the player gets an extra turn.
	 * 
	 * @param input
	 *            Whether or not to give an extra turn.
	 */
	public void TESTsetExtraTurn(boolean input) {
		testExtraTurn = input;
	}

	/**
	 * Method getPlayer(): Returns the array.
	 * 
	 * @return Return a Player array.
	 */
	public Player[] getPlayers() {
		return players;
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

	/**
	 * Method that checks if all players except one has lost the game.
	 * 
	 * @return Player winner.
	 */
	public Player checkForWinner() {
		Player winningPlayer = null;
		for (int i = 0; i < players.length; i++) {
			if (!players[i].getHasLost())
				if (winningPlayer == null)
					winningPlayer = players[i];
				else
					return null;

		}
		return winningPlayer;
	}

	/**
	 * Method FieldController: Return the field controller from the Main.
	 * 
	 * @return FieldController.
	 */
	public FieldController getLandOnFieldController() {
		return fieldController;
	}
}
