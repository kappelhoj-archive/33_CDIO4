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
//				inPrison = false;
				mainController.movePlayer(diceSum);
				mainController.playTurn();
			}			
//			else
//			{
//				inPrison = true;
//			}
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