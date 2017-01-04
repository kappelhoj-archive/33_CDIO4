package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.field.*;
import entity.GameBoard;

public class LandOnFieldController {

	
	/**
	 * Method landOnField decides what to be done when player lands on a Ownable Field.
	 * @param player The player to land on the field.
	 */
	public void landOnField(Field field, Player player)
	{
		String type = field.getType();
		switch(type)
		{
		case "Ownable": landOnOwnable(field, player);
			break;
		case "ChanceField": landOnChanceField(player);
			break;
		case "Tax": landOnTax(player);
			break;
		defualt: landOnNeutral(player);
			break;
		}
	}
	
	public void landOnOwnable(Field field, Player player)
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
				player.payRent(ownable.getOwner(), field.getRent());
			}
		}
	}
	
	
	/**
	 * Method landOnField decides what to be done when player lands on a Tax Field.
	 * @param player The player to land on the tax field. 5(valg) 39
	 */
	@Override
	public void landOnTax(Field field, Player player)
	{
		Tax tax = (Tax)(field);
		if (fields[player.getPosition()].get == 39)
		
		
		
		
		if (getPlayerPayDecision()) //Checks if the player wants to pay taxrate or fixed taxAmount. True if he wants to pay taxRate
		{
			int rent = (int)(0.01 * fielRate * player.getPlayerFortune()); //The rent to be paid
			player.changeAccountBalance(-rent);						  //Subtracts the rent from the balance of the player.
		}
		else
		{
			player.changeAccountBalance(-taxAmount);                  //Subtracts the rent from the balance of the player.
		}
	}
}
