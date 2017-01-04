package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.field.*;

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
}
