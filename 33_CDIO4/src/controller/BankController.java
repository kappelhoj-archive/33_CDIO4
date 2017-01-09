package controller;

import desktop_resources.GUI;
import entity.field.Ownable;
import entity.field.Street;
import entity.Account;
import entity.Player;
public class BankController 
{


	/**
	 * The method houseSaleBank conducts a transaction,
	 * when a player wants to buy a house.
	 * @param account The player-class account is needed to subtract the money from their account balance.
	 * @param street The street-class is needed to draw upon information like house pricing, and street name.
	 */
	public void houseBuyBank (Account account, Street street){

		if (account.getBalance() < street.getHousePrice())
		{
			GUI.getUserButtonPressed("Du har ikke råd til at købe et nyt hus til " + street.getName() + ".", "Ok");
		}
		else
		{
			account.changeBalance(street.getHousePrice());
			GUI.getUserButtonPressed("Du har købt ét nyt hus på " + street.getName(), "Ok");
		}
	}

	/**
	 * The method houseSaleBank conducts a transaction,
	 * when a player wants to sell a house back to the bank.
	 * @param account The players account is needed to add the money to their account balance.
	 * @param street The street is needed to draw upon information like number of current houses, house pricing, and street name.
	 */
	public void houseSaleBank (Account account, Street street){

		if (street.getNumbOfHouses()==0)
		{
			GUI.getUserButtonPressed("Der står ingen huse på " + street.getName(), "Ok");
		}
		else
		{
			street.changeNumbOfHouses(-1);
			account.changeBalance(street.getHousePrice()/2);
			GUI.getUserButtonPressed("Du solgte ét hus på " + street.getName() + "for " + street.getHousePrice()/2 + " kr.", "Ok");
		}

	}


	/**
	 * The method chancePaymentChecker checks if the player has enough funds
	 * to pay his or hers Chancecard. If the player doesn't haven enough funds,
	 * the player will lose the game.
	 * @param chancecard The chancecard dictates what type of payment is either requested off the player, or given to the player. 
	 * @param player The player will lose if they can not pay their rent.
	 */
	public void chancePaymentChecker (int chancePayment, Player player)
	{
		if (!playerAffordPayment(player,chancePayment))
		{
			GUI.getUserButtonPressed("Du har ikke nok midler til at betale din leje. Du har tabt, og udgår fra spillet.", "Ok");
			playerHasLost(player);
		}
		else if (chancePayment < 0 && player.getFortune() > chancePayment)
		{
			player.changeAccountBalance(chancePayment);
			GUI.getUserButtonPressed("Du betaler " + chancePayment + " kr. til banken", "Ok");
		}

		else if (chancePayment > 0)
		{
			GUI.getUserButtonPressed("Du modtager " + chancePayment + " kr. af banken.", "Fuck yea.");
			player.changeAccountBalance(chancePayment);
		}

		else		
		{
			GUI.getUserButtonPressed("Du betaler " + chancePayment + " kr. til banken.", "Fuck yea.");
			player.changeAccountBalance(chancePayment);
		}
	}
	
	/**
	 * The method playerAffordPayment checks to see if a player can afford
	 * a payment. If the player can not afford the required payment,
	 * the player loses the game.
	 * @param player The player to be checked.
	 * @param payment The payment to be withdrawn from the player's account.
	 * @return the current state of the player.setLost() condition.
	 * If true, the player will lose. If false, the player may pay the payment, and continue playing.
	 */
	public boolean playerAffordPayment (Player player, int payment)
	{
		if (player.getAccountBalance() < payment)
		{
			player.setHasLost(true);
			return false;
		}
		else
			return true;
	}
	/**
	 * The method playerHasLost is a method which, when a player loses,
	 * sets all of the player's owned fields free for other players to purchase again.
	 * @param player The affected player.
	 * 
	 */
	public void playerHasLost (Player player)
	{
		Ownable[] loseAllFields;

		if (player.getFields()!=null)
		{

			loseAllFields = new Ownable[player.getFields().length];

			for(int i = 0; i < player.getFields().length; i++)
			{
				player.loseFields(loseAllFields[i]);
			}
		}
	}
}