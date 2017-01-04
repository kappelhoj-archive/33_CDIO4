package controller;

import desktop_resources.GUI;
import entity.Player;

public class PrisonController {
	
	private MainController mainController;

	public void sentToPrison(Player player)
	{
		player.setPrison(true);
		player.setPosition(31);
		mainController.movePlayerOnGUI();
	}
	
	public boolean inPrison()
	{
		GUI.getUserSelection("Du er i fængsel, hvad vil du gøre?", "Betal dig ud: 1.000,-", "Rul med terningerne");
		
		
		mainController.rollDice();
		if(mainController.checkForExtraTurn())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}