package controller;

import desktop_resources.GUI;
import entity.Player;

/**
 * This class is responsible for anything to do with the prison.
 * 
 * @author Gruppe33
 *
 */
public class PrisonController {

	// Instance variables
	private MainController mainController;
	private DebtController bankController;

	/**
	 * Constructor: Constructs a PrisonController.
	 * 
	 * @param mainController
	 *            MainController.
	 * @param bankController
	 */
	public PrisonController(MainController mainController, DebtController bankController) {
		this.mainController = mainController;
		this.bankController = bankController;
	}

	/**
	 * Method sendToPrison: Sends a player to the prison.
	 * 
	 * @param player
	 *            Player to be send to prison.
	 */
	public void sendToPrison(Player player) {
		player.setInPrison(true);
		GUI.getUserButtonPressed("De fængsles.", "Afslut tur");
		player.setPosition(11);
		mainController.movePlayerOnGUI();

	}

	/**
	 * Method inPrison: Controls the prison turn.
	 * 
	 * @param player
	 *            Player in prison.
	 * @return boolean True if the player can roll after he gets out of prison
	 *         otherwise false.
	 */
	public boolean inPrison(Player player) {
		boolean boughtOut = false;
		String payOut = "Betal dig ud: 1.000 kr";
		String rollOut = "Slå dig ud fængslet med terningerne";
		String userDecision = null;
		// If this is your fourth round in jail.
		if (player.getTurnsInPrison() >= 3) {
			player.setInPrison(false);
			player.setTurnsInPrison(0);
			GUI.getUserButtonPressed("Du har nu siddet i fængsel i 3 runder, og bliver derfor sat fri.", "Ok");
			return true;
		}
		// If you can afford to pay yourself out of jail.
		if (player.getAccountBalance() > 1000) {
			userDecision = GUI.getUserButtonPressed("Du er i fængsel, hvad vil du gøre?", payOut, rollOut);
		} else if (((player.getFortune() - player.getAccountBalance()) / 2) + player.getAccountBalance() >= 1000) {
			userDecision = GUI.getUserButtonPressed(
					"Du er i fængsel, hvad vil du gøre?\n"
							+ "Du har dog ikke råd til at betale dig ud, så hvis du vil gøre det, er du nødt til at sælge bygninger eller grunde.",
					payOut, rollOut);
		} else {
			userDecision = GUI.getUserButtonPressed(
					"Du er i fængsel og har ikke mulighed for at betale dig ud, da du ikke har råd.", rollOut);
		}

		// If the player chooses to pay himself out, release him from jail.
		if (userDecision.equals(payOut)) {
			if (bankController.playerAffordPayment(player, 1000)) {
				player.changeAccountBalance(-1000);
				GUI.setBalance(player.getName(), player.getAccountBalance());
				player.setInPrison(false);
				boughtOut = true;
			}
		}
		// Roll the dice and do whats appropiate.
		else {
			int diceSum = mainController.rollDice();
			player.changeTurnsInPrison(1);
			if (mainController.checkForExtraTurn()) {
				GUI.getUserButtonPressed("Du slog to ens! Du er nu fri og må slå igen.", "Slå med terningerne");
				player.setInPrison(false);
				mainController.movePlayer(diceSum);
				mainController.getLandOnFieldController().landOnField(player, diceSum);
				mainController.playerTurnDecision();
			} else {
				GUI.getUserButtonPressed("Du slog dig ikke ud af fængslet", "Afslut tur");
			}
		}
		return boughtOut;
	}

	/**
	 * Method checkInPrison: Checks whether a player is in prison or not.
	 * 
	 * @param player
	 *            Player to check for.
	 * @return True if the player is in prison.
	 */
	public boolean checkInPrison(Player player) {
		if (player.getInPrison())
			return true;
		else
			return false;
	}
}