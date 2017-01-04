package controller;

import desktop_resources.GUI;
import entity.field.Street;
import entity.Player;
import entity.Account;
public class Bank 
{

	/**
	 * The method houseSaleBank conducts a transaction,
	 * when a player wants to buy a house.
	 */
	public void houseSaleBank (Account account, Street street){

		String houseSale = "Køb ét hus for: " + street.getHousePrice()+ "kr.";
		String denyHouseSale = "Afslå køb.";
		String input = GUI.getUserSelection("Du kan vælge at købe ét hus for " + street.getHousePrice() + "kr.", houseSale, denyHouseSale);

		if(input.equals(denyHouseSale))
		{
			System.out.println("Du valgte ikke at købe et nyt hus.");
		}

		if (input.equals(houseSale) && account.getBalance()<street.getHousePrice())
		{
			System.out.println("Du har ikke råd til at købe et nyt hus til din grund.");
		}
		else
		{
			account.changeBalance(street.getHousePrice());
			System.out.println("Du har købt ét nyt hus på " + street.getName());
		}
	}
	
	/**
	 * The method houseBuyBank conducts a transaction,
	 * when a player wants to sell a house back to the bank.
	 */
	public void houseBuyBank (Account account, Street street){
		String houseBuy = "Sælg et hus tilbage til banken for halvdelen af købsprisen.";
		String denyHouseBuy= "Afslå salg";
		String input = GUI.getUserSelection("Sælg ét hus tilbage til banken for " + street.getHousePrice() + " kr.", houseBuy, denyHouseBuy);
	
		if (input.equals(denyHouseBuy))
		{
			System.out.println("Du valgte ikke at sælge et hus tilbage til banken.");
		}
		
		if (input.equals(houseBuy) && street.getNumbOfHouses()==0)
		{
			System.out.println("Der står ingen huse på " + street.getName());
		}
		else
		{
			street.setNumbOfHouses();
			account.changeBalance(street.getHousePrice()/2);
			System.out.println("Du solgte ét hus på " + street.getName() + "for " + street.getHousePrice()/2 + " kr.");
		}
	
	}

	

}