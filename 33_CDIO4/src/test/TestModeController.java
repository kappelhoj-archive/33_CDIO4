package test;

import controller.MainController;
import desktop_resources.GUI;
import entity.Player;

public class TestModeController {
	private boolean testModeStatus;
	private String testingModeMessage = "*****DU ER I TESTING MODE*****\n";

	public TestModeController(String testModeStatus) {
		if (testModeStatus.toLowerCase().equals("testmode")) {
			activateTestMode();
		} else {
			endTestMode();
		}
	}

	private void activateTestMode() {
		testModeStatus = true;
		System.out.println("Test mode er aktiveret.");
	}

	private void endTestMode() {
		testModeStatus = false;
	}

	private int setPlayerOnField(Player player) {
		int newFieldPos = GUI.getUserInteger(
				testingModeMessage + "Hvilket felt vil du rykke spilleren til?\n"
						+ "(Bemærk at spillere bevæger sig rundt på pladen med denne funktion. Så han får start penge.)",
				1, 40);
		int diff = newFieldPos - player.getPosition();
		if (diff < 0) {
			diff = (40 - player.getPosition()) + player.getPosition() + diff;
		}

		return diff;
	}

	private void givePlayerExtraTurn(MainController main) {
		GUI.getUserButtonPressed(testingModeMessage + "Bemærk at spilleren stadig sendes i fængsel efter 3 gange 2 ens",
				"OK");
		main.TESTsetExtraTurn(true);
	}

	private void setPlayerBalance(Player player) {
		int newBalance = GUI.getUserInteger(testingModeMessage + "Hvad skal den nye balance være?", 0, 10000000);
		player.changeAccountBalance(newBalance - player.getAccountBalance());
	}

	public int options(Player player, MainController main) {
		int output = -1;

		final String MOVE_PLAYER_TO_FIELD = "Ryk spilleren til et felt.";
		final String EXTRA_TURN = "Giv denne spiller en ekstra tur.";
		final String SET_PLAYER_BALANCE = "Ændre spillerens balance.";
		final String CLAIM_FIELD="Giv spilleren et felt, kan også stjæle fra andre spillere.";
		final String DEACTIVATE_TEST_MODE = "Deaktiver testmode.";
		final String STOP_TEST_MODE = "Fortsæt spil.";

		String input;
		boolean menuActive = true;
		if (player.getPrison()) {
			input = GUI.getUserSelection(testingModeMessage + "Hvad vil du gøre?", "Slip ud af fængslet.",
					"FortsætSpil");
			if (input.equals("Slip ud af fængslet.")) {
				main.TESTsetExtraTurn(true);
				player.setPrison(false);
			}
		} else
			do {
				input = GUI.getUserSelection(testingModeMessage + "Hvad vil du gøre?", MOVE_PLAYER_TO_FIELD, EXTRA_TURN,
						SET_PLAYER_BALANCE, STOP_TEST_MODE, DEACTIVATE_TEST_MODE);
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

				// Claim et felt for spilleren.

				}
			} while (menuActive);

		return output;
	}

	public boolean isActive() {
		return testModeStatus;
	}

}
