package controller;

import desktop_resources.GUI;
import entity.field.Ownable;
import entity.field.Street;
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
			GUI.getUserButtonPressed("Der står ingen huse på " + street.getName() + ".", "Ok");
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
					"Du solgte ét hus på " + street.getName() + " for " + street.getHousePrice() / 2 + " kr.", "Ok");
		}
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

			for (int i = 0; i < allFields.length; i++) {
				if (allFields[i] instanceof Street) {
					Street temp = (Street) allFields[i];
					if (temp.getNumbOfHouses() == 5) {
						housesCon.changeHotels(1);
					} else {
						housesCon.changeHouses(temp.getNumbOfHouses());
					}
					temp.changeNumbOfHouses(-temp.getNumbOfHouses());
					GUI.setHouses(temp.getFieldNumber(), temp.getNumbOfHouses());
					GUI.setHotel(temp.getFieldNumber(), false);
					GUI.removeOwner(temp.getFieldNumber());

				}
				player.removeField(allFields[i]);
			}
		}
		
		player.changeAccountBalance(-player.getAccountBalance()-1);
		GUI.setBalance(player.getName(), player.getAccountBalance());
		GUI.getUserButtonPressed("Du tabte, og udgår fra spillet.", "Ok");

	}

	public boolean handleDebt(Player player, int debt) {
		boolean whatever = false;
		if (whatever) {
			return true;
		}

		else {
			playerHasLost(player);
			return false;
		}
	}
}