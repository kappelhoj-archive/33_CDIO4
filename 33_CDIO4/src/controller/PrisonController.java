package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.DiceCup;

public class PrisonController {
	
	private MainController mainController;

	public void sentToPrison(Player player)
	{
		player.setPrison(true);
		player.setPosition(31);
		mainController.movePlayerOnGUI();
	}
	
	public boolean inPrison(Player player)
	{
		boolean inPrison;
		String payOut = "Betal dig ud: 1.000,-";
		String rollOut = "Rul med terningerne.";
		String input = GUI.getUserSelection("Du er i fængsel, hvad vil du gøre?", payOut, rollOut);
		
		if(input.equals(payOut))
		{
			player.setAccountBalance(-1000);
			player.setPrison(false);
			inPrison = false;
		}
		else
		{
			int diceSum = mainController.rollDice();
			if(mainController.checkForExtraTurn())
			{
				inPrison = false;
				mainController.movePlayer(diceSum);
				mainController.playTurn();
			}			
			else
			{
				inPrison = true;
			}
		}
		
		return inPrison;
	}
	
	
}