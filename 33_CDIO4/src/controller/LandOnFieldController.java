package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.field.*;
import entity.GameBoard;

public class LandOnFieldController {

	private PrisonController prisonController;
	private ChanceCardController chanceCardController;
	
	public LandOnFieldController(PrisonController pc, MainController mc)
	{
		this.prisonController = new PrisonController(mc);
		this.chanceCardController = new ChanceCardController();
	}
	
	/**
	 * Method landOnField: Decides what has to be done when a player lands on a field.
	 * @param field The field the player landed on.
	 * @param player The player to land on the field.
	 */
	public void landOnField(Field field, Player player, int diceSum)
	{
		String type = field.getType();
		switch(type)
		{
		case "Ownable": landOnOwnable(field, player, diceSum);
			break;
		case "ChanceField": landOnChanceField(player);
			break;
		case "Tax": landOnTax(field, player);
			break;
		default: landOnNeutral(player);
			break;
		}
	}
	
	/**
	 * Method landOnOwnable: Decides what has to be done when a player lands on an ownable field.
	 * @param field The field the player landed on.
	 * @param player The player to land on the field.
	 */
	public void landOnOwnable(Field field, Player player, int diceSum)
	{
		Ownable ownable = (Ownable)(field);
		if(!ownable.isFieldOwned())
		{
			boolean bought = GUI.getUserLeftButtonPressed("Vil du købe " + field.getName() + "?", "Yes", "No");
			if(bought)
			{
			player.buyField(field);
			}
		}
		else
		{
			if(ownable.isFieldOwnedByAnotherPlayer(player))
			{
				if (field.getType().equals("Brewery"))
				{
					Brewery brewery = (Brewery)(field);
					brewery.setDiceSum(diceSum);
				}
				GUI.getUserButtonPressed("Du skal betale " + field.getRent() + " til " + ownable.getOwner().getName() + ".", "Ok");
				player.payRent(ownable.getOwner(), field.getRent());
			}
		}
	}
	
	
	/**
	 * Method landOnField: Decides what has to be done when a player lands on a Tax field.
	 * @param field The field the player landed on.
	 * @param player The player to land on the tax field.
	 */
	public void landOnTax(Field field, Player player)
	{
		int rent = 0;
		Tax tax = (Tax)(field);
		if (tax.getTaxRateAvailable() == true)
		{
			boolean percent = GUI.getUserLeftButtonPressed("Betal indkomstskat: 10% eller 4.000 kr.","10%","4.000");
			if (percent)
			{
				rent = (int)(0.1) * player.getFortune(); //The rent to be paid.
				player.changeAccountBalance(-rent);      //Subtracts the rent from the balance of the player.
			}
			else
			{
				rent = 4000;							 //The rent to be paid.
				player.changeAccountBalance(-rent);      //Substracts the rent from the balance of the player.
			}
		}
		else
		{
			GUI.getUserButtonPressed("Ekstraordinærstatsskat: Betal 2.000 kr.", "Ok");
			rent = 2000;                                 //The rent to be paid.
			player.changeAccountBalance(-rent);          //Subtracts the rent from the balance of the player.
		}
		
	}
	
	/**
	 * Method landOnNeutral: Decides what has to be done when a player lands on the following fields: <br>
	 * - Sent to prison <br>
	 * - Start <br>
	 * - Parking <br>
	 * - Visiting prison. <br>
	 * @param player The player who landed on the neutral field.
	 */
	public void landOnNeutral(Player player){
		int isPrison = 30;
		if (player.getPosition() == isPrison)
		{
			GUI.getUserButtonPressed("De fængsels", "Ok");
			prisonController.sentToPrison(player);
		}
	}
	
	/**
	 * Method landOnChanceField: Decides what has to be done when a player lands on a chance field.
	 * @param player The player who landed on the chance field.
	 */
	public void landOnChanceField(Player player)
	{
		GUI.getUserButtonPressed("Prøv lykken", "Ok");
		chanceCardController.draw(player);
	}
}
