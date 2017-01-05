package controller;

import desktop_resources.GUI;
import entity.field.Street;
import entity.Account;
import entity.Player;
import entity.ChanceCard.*;
public class Bank 
{

	private int chancePayment = 0;


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
			System.out.println("Der står ingen huse på " + street.getName());
		}
		else
		{
			street.subtractNumbOfHouses();
			account.changeBalance(street.getHousePrice()/2);
			System.out.println("Du solgte ét hus på " + street.getName() + "for " + street.getHousePrice()/2 + " kr.");
		}

	}

	public int changeChanceCardPayment(ChanceCard chancecard)
	{
		chancePayment += chancecard.chanceCardSum;
		return chancePayment;
	}

	/**
	 * The method chancePaymentChecker checks if the player has enough funds
	 * to pay his or hers Chancecard. If the player doesn't haven enough funds,
	 * the player will lose the game.
	 * @param chancecard The chancecard dictates what type of payment is either requested off the player, or given to the player. 
	 * @param player The player will lose if they can not pay their rent.
	 */
	public void chancePaymentChecker (ChanceCard chancecard, Player player, Street street)
	{
		if (chancePayment < 0 && player.getFortune() < chancePayment)
		{
			System.out.println("Du har ikke nok midler til at betale din leje. Du har tabt, og udgår fra spillet.");
			player.setLost(true);
		}

		if (chancePayment > 0)
		{
			System.out.println("Du modtager " + chancePayment + " kr. af banken.");
			player.changeAccountBalance(chancePayment);

		}
		else		
		{
			System.out.println("Du betaler " + chancePayment + " kr. til banken.");
		}
	}

	public boolean playerAffordPayment (Player player, int payment)
	{
		if (player.getAccountBalance() < payment)
		{
			return false;
		}
		else
			return true;
	}
	
	public void playerHasLost (Player player, Street street)
	{
		player.set
	}
	
}