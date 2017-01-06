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
		GUI.getUserButtonPressed("Du er blevet smidt i fængsel", "Ok");
	}
	
	public void inPrison(Player player)
	{
//		boolean inPrison = false;
		String payOut = "Betal dig ud: 1.000,-";
		String rollOut = "Rul med terningerne.";
		String input = GUI.getUserSelection("Du er i fængsel, hvad vil du gøre?", payOut, rollOut);
		
		if(input.equals(payOut))
		{
			player.changeAccountBalance(-1000);
			player.setPrison(false);
//			inPrison = false;
		}
		else
		{
			int diceSum = mainController.rollDice();
			if(mainController.checkForExtraTurn())
			{
				GUI.getUserButtonPressed("Du slog to ens! Du er nu fri og må slå igen.", "Ok");
				mainController.movePlayer(diceSum);
				mainController.playTurn();
			}			
			else
			{
				GUI.getUserButtonPressed("Du slog dig ikke ud af fængslet", "Ok");
			}
		}
		
//		return inPrison;
	}
	
	public boolean checkIfInPrison(Player player)
	{
		if(player.getPrison())
			return true;
		else
			return false;
	}
}