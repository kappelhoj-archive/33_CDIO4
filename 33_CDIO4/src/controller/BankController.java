package controller;

import desktop_resources.GUI;
import entity.field.Field;
import entity.field.Ownable;
import entity.field.Street;
import entity.Account;
import entity.Player;

public class BankController {
	private PropertyController housesCon;

	BankController(PropertyController houseCon) {
		this.housesCon = houseCon;
	}

	/**
	 * The method houseSaleBank conducts a transaction, when a player wants to
	 * sell a house back to the bank.
	 * 
	 * @param account
	 *            The players account is needed to add the money to their
	 *            account balance.
	 * @param street
	 *            The street is needed to draw upon information like number of
	 *            current houses, house pricing, and street name.
	 */
	public void sellHouse(Player player, Street street) {

		if (street.getNumbOfHouses() == 0) {
			GUI.getUserButtonPressed("Der står ingen huse på " + street.getName(), "Ok");
		} else {
			if (street.getNumbOfHouses() == 5) {
				housesCon.changeHotels(1);
				housesCon.changeHouses(-4);
				//Der er en fejl her hvor spillerne kan få huse selvom der ikke er nok huse.
			} else {
				housesCon.changeHouses(1);
			}

			street.changeNumbOfHouses(-1);
			player.changeAccountBalance(street.getHousePrice() / 2);
			GUI.getUserButtonPressed(
					"Du solgte ét hus på " + street.getName() + "for " + street.getHousePrice() / 2 + " kr.", "Ok");
		}
	}

	public void sellField(Player player, Ownable field)
	{
		player.changeAccountBalance(field.getPrice()/2);
		player.loseFields(field);
	}

	/**
	 * The method playerAffordPayment checks to see if a player can afford a
	 * payment. If the player can not afford the required payment, the player
	 * loses the game.
	 * 
	 * @param player
	 *            The player to be checked.
	 * @param payment
	 *            The payment to be withdrawn from the player's account.
	 * @return the current state of the player.setLost() condition. If true, the
	 *         player will lose. If false, the player may pay the payment, and
	 *         continue playing.
	 */
	public boolean playerAffordPayment(Player player, int payment) {
		if (player.getAccountBalance() < payment) {
			handleDebt(player, payment);

			return false;
		} else
			return true;
	}

	/**
	 * The method playerHasLost is a method which, when a player loses, sets all
	 * of the player's owned fields free for other players to purchase again.
	 * 
	 * @param player
	 *            The affected player.
	 * 
	 */
	public void playerHasLost(Player player) {
		Ownable[] allFields;
		player.setHasLost(true);

		if (player.getFields() != null) {

			allFields = new Ownable[player.getFields().length];
			allFields = player.getFields();

			for (int i = 0; i < player.getFields().length; i++) {
				if (allFields[i] instanceof Street) {
					Street temp = (Street) allFields[i];
					if (temp.getNumbOfHouses() == 5) {
						housesCon.changeHotels(1);
					} else {
						housesCon.changeHouses(temp.getNumbOfHouses());
					}
					temp.changeNumbOfHouses(-temp.getNumbOfHouses());

				}
				player.loseFields(allFields[i]);
			}
		}
		GUI.getUserButtonPressed("Du tabte spillet.", "Ok");

	}

	public boolean handleDebt(Player player, int debt) {
		if(player.getFortune() > debt)
		{
			final String SELLFIELD = "Sælg grund";
			final String SELLHOUSE = "Sælg hus";
			String userSelection = GUI.getUserSelection("Du har ikke råd til at betale din gæld, hvad vil du gøre?", SELLFIELD, SELLHOUSE);
			Ownable[] ownedFields = player.getFields();
			String[] ownedFieldsNames = new String[ownedFields.length];
			
			
			for (int i = 0; i < ownedFields.length; i++)
			{
				ownedFieldsNames[i] = ownedFields[i].getName();
			}

			switch(userSelection)
			{
			case SELLFIELD: GUI.getUserSelection("Hvilken grund vil du sælge?", ownedFieldsNames);
			break;
			case SELLHOUSE: GUI.getUserSelection("På hvilken grund vil du sælge et hus?", streetsWithMostHouses(ownedFields));
			break;
			}
			return true;
		}

		else {
			playerHasLost(player);
			return false;
		}
	}
	
	
	///////////////////////////
	// Salg af huse
	//////////////////////////
	
	private int maximum(int[] numbers) {
		//Set an abretary high value
		int max = 0;
		
		for(int i = 0; i < numbers.length; i++)
		{
			max = Math.max(max, numbers[i]);
		}
		return max;
	}
	
	private String[] streetsWithMostHouses(Ownable[] streets) {
		
		Street[] streetsArr = (Street[])streets;
		//An array to hold the amount of houses on the different streets.
		int[] houses = new int[3];
		String[] streetNames = new String[3];
		
		for(int k = 0; k < streetsArr.length; k++)
		{
			houses[k] = streetsArr[k].getNumbOfHouses();
			streetNames[k] = streetsArr[k].getName();
		}
		
		// Finds which street has the most houses.
		int maximum = maximum(houses);
		
		// Hvis der ikke er nogen huse på en grund, så skal der ikke kunne sælges huse på den grund
		if (maximum == 0){
			return null;
		}
		
		int amountOfStreets = 0;
		
		//Finds the streets that has the most houses
		for (int i = 0; i < streetNames.length; i++) {
			if (houses[i] == maximum) {
				amountOfStreets++;
			}
		}
		//An array to hold the name of the differents streets that you can build on.
		String[] streetNamesNew = new String[amountOfStreets];
		int j = 0;
		for (int i = 0; i < streetNames.length; i++) {
			if (maximum == houses[i]) {
				streetNamesNew[j] = streetNames[i];
				j++;
			}
		}
		return streetNamesNew;
	}
}