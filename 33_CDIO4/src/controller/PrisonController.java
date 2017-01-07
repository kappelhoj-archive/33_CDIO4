package controller;

import desktop_resources.GUI;
import entity.Player;

public class PrisonController {
	
	private MainController mainController;
	
	public PrisonController(MainController main)
	{
		mainController = main;
	}

	public void sentToPrison(Player player)
	{
		player.setPrison(true);
		player.setPosition(31);
		mainController.movePlayerOnGUI();
		GUI.getUserButtonPressed("De fængsles.", "Ok");
	}
	
	public boolean inPrison(Player player)
	{
		boolean paidOut = false;
		String payOut = "Betal dig ud: 1.000 kr";
		String rollOut = "Prøv at slå dig ud med terningerne";
		String input = GUI.getUserButtonPressed("Du er i fængsel, hvad vil du gøre?", payOut, rollOut);
		
		if(input.equals(payOut))
		{
			player.changeAccountBalance(-1000);
			GUI.setBalance(player.getName(), player.getAccountBalance());
			player.setPrison(false);
			paidOut = true;
			GUI.getUserButtonPressed("Du valgte at betale dig ud af fængslet. Slå med terningerne for at rykke dine brik.", "Slå med terninger");
			int diceSum = mainController.rollDice();
			mainController.movePlayer(diceSum);
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
				GUI.getUserButtonPressed("Du slog dig ikke ud af fængslet", "Ok");
			}
		}
		return paidOut;
	}
	
	public boolean checkIfInPrison(Player player)
	{
		if(player.getPrison())
			return true;
		else
			return false;
	}
}