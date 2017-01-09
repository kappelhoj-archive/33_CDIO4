package test.controller;

import entity.Player;

public class PrisonControllerTestStub {
	
	// Instance variables
	private MainControllerTestStub mainController;
	
	/**
	 * PrisonController constructor.
	 * @param mainController MainController.
	 */
	public PrisonControllerTestStub(MainControllerTestStub mainController)
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
		player.setPosition(31);
	}
	
	/**
	 * Method inPrison controls the prison turn.
	 * @param player Player in prison.
	 * @return boolean Returns a boolean whether a player bought himself out of the prison or not.
	 */
	public boolean inPrison(Player player, String payOut)
	{
		boolean boughtOut = false;
		String input = "payOut";
		
		if(input.equals(payOut))
		{
			player.changeAccountBalance(-1000);
			player.setInPrison(false);
			boughtOut = true;
			int diceSum = mainController.rollDice();
			mainController.movePlayer(diceSum);
		}
		else
		{
			int diceSum = mainController.rollDice();
			if(mainController.checkForExtraTurn())
			{
				mainController.movePlayer(diceSum);
				mainController.playTurn();
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