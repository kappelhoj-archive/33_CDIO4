package controller;

import desktop_resources.GUI;
import entity.Player;

public class PrisonController {
	
	// Instance variables
	private MainController mainController;
	
	/**
	 * PrisonController constructor.
	 * @param mainController MainController.
	 */
	public PrisonController(MainController mainController)
	{
		this.mainController = mainController;
	}

	/**
	 * Method sendToPrison sends a player to the prison.
	 * @param player Player to be send to prison.
	 */
	public void sendToPrison(Player player)
	{
		player.setInPrison(true);
		GUI.getUserButtonPressed("De fængsles.", "Afslut tur");
		player.setPosition(11);
		mainController.movePlayerOnGUI();	

	}
	
	/**
	 * Method inPrison controls the prison turn.
	 * @param player Player in prison.
	 * @return boolean Returns a boolean whether a player bought himself out of the prison or not.
	 */
	public boolean inPrison(Player player)
	{
		boolean boughtOut = false;
		String payOut = "Betal dig ud: 1.000 kr";
		String rollOut = "Slå dig ud fængslet med terningerne";
		String input = null;
		
		if(player.getAccountBalance() > 1000)
		{
			input = GUI.getUserButtonPressed("Du er i fængsel, hvad vil du gøre?", payOut, rollOut);
		}
		else 
		{
			input = GUI.getUserButtonPressed("Du er i fængsel og har ikke mulighed for at betale dig ud, da du ikke har råd.", rollOut);
		}	
		if(input.equals(payOut))
		{
			player.changeAccountBalance(-1000);
			GUI.setBalance(player.getName(), player.getAccountBalance());
			player.setInPrison(false);
			boughtOut = true;
		}
		else
		{
			int diceSum = mainController.rollDice();
			if(mainController.checkForExtraTurn())
			{
				GUI.getUserButtonPressed("Du slog to ens! Du er nu fri og må slå igen.", "Slå med terningerne");
				mainController.movePlayer(diceSum);
				mainController.playTurn();
			}			
			else
			{
				GUI.getUserButtonPressed("Du slog dig ikke ud af fængslet", "Afslut tur");
			}
		}
		return boughtOut;
	}
	
	/**
	 * Method checkInPrison checks whether a player is in prison or not.
	 * @param player Player to check for.
	 * @return boolean.
	 */
	public boolean checkInPrison(Player player)
	{
		if(player.getInPrison())
			return true;
		else
			return false;
	}
}