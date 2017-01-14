package testModeController;


import entity.field.*;
import controller.FieldController;
import controller.MainController;
import desktop_resources.GUI;
import entity.GameBoard;
import entity.Player;

/**
 * This class is a controller that interferes with normal game play.  This allows testers to test the program more easily.
 * @author Gruppe33
 *
 */
public class TestModeController {
	private boolean testModeStatus;
	private String testingModeMessage = "*****DU ER I TESTING MODE*****\n";

	/**
	 * Constructor: Constructs a TestModeController.
	 * 
	 * @param testmodeOn
	 */
	public TestModeController(boolean testmodeOn) {
		if (testmodeOn) {
			activateTestMode();
		} else {
			endTestMode();
		}
	}

	/**
	 * Method activateTestMode: setsTestModeStatus to true. This will start
	 * testmode in the game.
	 */
	private void activateTestMode() {
		testModeStatus = true;
		System.out.println("Test mode er aktiveret.");
	}

	/**
	 * Method endTestMode: setsTestModeStatus to false. This will end testmode
	 * in the game.
	 */
	private void endTestMode() {
		testModeStatus = false;
	}

	/**
	 * Method setPlayerOnField: Moves the player to a specific field given a
	 * field number by the player.
	 * 
	 * @param player
	 *            The player whos turn it is.
	 * @return The amount of fields the player has to move forward to reach the
	 *         field.
	 */
	private int setPlayerOnField(Player player) {
		int newFieldPos = GUI.getUserInteger(
				testingModeMessage + "Hvilket felt vil du rykke spilleren til?\n"
						+ "(Bemærk at spillere bevæger sig rundt på pladen med denne funktion. Så han får start penge.)",
				1, 40);
		//Find the difference in position.
		int diff = newFieldPos - player.getPosition();
		//If the difference is less than 0, add the extra value he has to walk.
		if (diff < 0) {
			diff = (40 - player.getPosition()) + player.getPosition() + diff;
		}
		
		//Manipulate some GUI output
		GUI.removeAllCars(player.getName());
		int printpos = player.getPosition() + diff;
		if (printpos > 40) {
			printpos -= 40;
		}
		GUI.setCar(printpos, player.getName());

		return diff;
	}

	/**
	 * Method givePlayerExtraTurn: Forces the player to receive an extra turn.
	 * Also counts as an extra turn when going to jail on your 3rd role.
	 * 
	 * @param main
	 *            The maincontroller.
	 */
	private void givePlayerExtraTurn(MainController main) {
		GUI.getUserButtonPressed(testingModeMessage + "Bemærk at spilleren stadig sendes i fængsel efter 3 gange 2 ens",
				"OK");
		main.TESTsetExtraTurn(true);
	}

	/**
	 * Method setPlayerBalance: Sets the players balance to a new value.
	 * 
	 * @param player
	 *            The player whos balance needs to be changed.
	 */
	private void setPlayerBalance(Player player) {
		int newBalance = GUI.getUserInteger(testingModeMessage + "Hvad skal den nye balance være?", 0, 10000000);
		//Change the players balance.
		player.changeAccountBalance(newBalance - player.getAccountBalance());
		GUI.setBalance(player.getName(), player.getAccountBalance());
	}

	/**
	 * Method claimField: Claims a field for the player. Now he owns that field
	 * number.
	 * 
	 * @param board
	 *            The boardGame in question.
	 * @param player
	 *            The players who claims the field.
	 */
	private void claimField(GameBoard board, Player player) {
		int fieldNum = GUI.getUserInteger("Hvilket felt vil du overtage?", 1, 40);
		//Claim the field.
		changeFieldOwnership(board, player, fieldNum);

	}

	/**
	 * Method changeField: Changes the ownership of a field to a specific
	 * player.
	 * 
	 * @param board
	 *            The boardGame in question.
	 * @param player
	 *            The players who claims the field.
	 * @param fieldNum
	 *            The fieldnumber of the field to be changed.
	 */
	private void changeFieldOwnership(GameBoard board, Player player, int fieldNum) {
		Field testField = board.getField(fieldNum);
		//If the field can be owned.
		if ((testField instanceof entity.field.Ownable)) {
			Ownable currentField = (Ownable) testField;
			//if the field is owned.
			if (currentField.getOwner() != null) {
				currentField.getOwner().removeField(currentField);
				currentField.removeOwner();
			}
			//Buy the field, and give the player what i payed for the field.
			player.changeAccountBalance(currentField.getPrice());
			player.buyField(currentField);
			GUI.setOwner(fieldNum, currentField.getOwner().getName());

		} else {
			GUI.getUserButtonPressed("Dette felt kan ikke købes", "Ok");
		}
	}

	/**
	 * Method claimColor: Changes the ownership of all fields of a specific
	 * color to the player.
	 * 
	 * @param board
	 *            The boardGame in question.
	 * @param player
	 *            The players who claims the field.
	 */
	private void claimColor(GameBoard board, Player player) {
		String[] streetColours = { "Blå", "Orange", "Grøn", "Grå", "Rød", "Hvid", "Gul", "Lilla" };
		String color = GUI.getUserSelection("Hvilken farve vil du købe?", streetColours);

		//Check all the fields, if they have the stated color change ownership to the player.
		for (int i = 0; i < 40; i++) {
			Field field = board.getField(i + 1);
			if (field instanceof entity.field.Ownable) {
				if (((Ownable) field).getColour().equals(color)) {
					changeFieldOwnership(board, player, field.getFieldNumber());

				}
			}
		}

	}

	/**
	 * Method options: A menu that contains the all the options the player has
	 * when testmode is active.
	 * 
	 * @param player
	 *            The player whos turn it is.
	 * @param main
	 *            The main controller
	 * @param fieldController
	 *            The fieldController.
	 * @return The dicesum output if it has changed. Otherwise -1.
	 */
	public int options(Player player, MainController main, FieldController fieldController) {
		int output = -1;

		final String MOVE_PLAYER_TO_FIELD = "Ryk til et felt.";
		final String EXTRA_TURN = "Giv en ekstra tur.";
		final String SET_PLAYER_BALANCE = "Ændre balance.";
		final String CLAIM_FIELD = "Giv en grund.";
		final String CLAIM_COLOR = "Giv en farve.";
		final String DEACTIVATE_TEST_MODE = "Deaktiver testmode.";
		final String STOP_TEST_MODE = "Fortsæt spil.";

		String input;
		boolean menuActive = true;
		if (player.getInPrison()) {
			input = GUI.getUserSelection(testingModeMessage + "Hvad vil du gøre?", "Slip ud af fængslet.",
					"FortsætSpil");
			if (input.equals("Slip ud af fængslet.")) {
				main.TESTsetExtraTurn(true);
				player.setInPrison(false);
			}
		} else
			do {
				input = GUI.getUserSelection(testingModeMessage + "Hvad vil du gøre?", MOVE_PLAYER_TO_FIELD, EXTRA_TURN,
						SET_PLAYER_BALANCE, CLAIM_FIELD, CLAIM_COLOR, STOP_TEST_MODE, DEACTIVATE_TEST_MODE);
				switch (input) {
				case MOVE_PLAYER_TO_FIELD:
					output = setPlayerOnField(player);
					break;
				case EXTRA_TURN:
					givePlayerExtraTurn(main);
					break;
				case SET_PLAYER_BALANCE:
					setPlayerBalance(player);
					break;
				case DEACTIVATE_TEST_MODE:
					//Deactivate the test mode.
					if (GUI.getUserLeftButtonPressed(
							testingModeMessage + "Er du sikker? Du kan ikke gå tilbage til testmode i denne session?",
							"Ja", "Nej")) {
						endTestMode();
						menuActive = false;
					}
					break;
				case STOP_TEST_MODE:
					menuActive = false;
					break;
				case CLAIM_FIELD:
					claimField(fieldController.TESTgetGameBoard(), player);
					break;
				case CLAIM_COLOR:
					claimColor(fieldController.TESTgetGameBoard(), player);
					break;


				}
			} while (menuActive);

		return output;
	}

	/**
	 * Method isActive: Returns wheter the testmode is active or not.
	 * 
	 * @return boolean that contain the status of the testmode.
	 */
	public boolean isActive() {
		return testModeStatus;
	}

}
