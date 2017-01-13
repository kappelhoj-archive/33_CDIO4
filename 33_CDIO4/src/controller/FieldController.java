package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.field.*;
import entity.GameBoard;

/**
 * This class is responsible for the fields. It controls what happens when the
 * players land on a field.
 * 
 * @author Gruppe33
 *
 */
public class FieldController {

	// Instance variables
	private PrisonController prisonController;
	private ChanceCardController chanceCardController;
	private DebtController bankController;
	private GameBoard gameBoard;
	private boolean doubleRent = false;

	/**
	 * Constructor: Constructs a FieldController
	 * 
	 * @param prisonController
	 *            The PrisonController.
	 * @param mainController
	 *            The MainController.
	 * @param propertyController
	 *            The PropertyController.
	 */
	public FieldController(PrisonController prisonController, MainController mainController,
			BuildingController propertyController, DebtController bankController) {
		this.bankController = bankController;
		this.prisonController = prisonController;
		this.chanceCardController = new ChanceCardController(prisonController, bankController, mainController);
		this.gameBoard = new GameBoard();
	}

	/**
	 * Method landOnField: Decides what has to be done when a player lands on a
	 * field.
	 * 
	 * @param player
	 *            The player to land on the field.
	 * @param diceSum
	 *            The dice sum of the player's dice roll.
	 */
	public void landOnField(Player player, int diceSum) {

		Field field = gameBoard.getField(player.getPosition());
		String type = field.getType();
		switch (type) {
		case "Ejendom":
		case "Rederi":
		case "Tapperi":
			landOnOwnable(field, player, diceSum);
			break;
		case "Chancekort":
			landOnChanceField(player);
			break;
		case "Skat":
			landOnTax(field, player);
			break;
		default:
			landOnNeutral(player);
			break;
		}
		// Reset the doubleRent, this variable is used in one type of chanceCard
		doubleRent = false;
	}

	/**
	 * Method landOnOwnable: Decides what has to be done when a player lands on
	 * an ownable field.
	 * 
	 * @param field
	 *            The field the player landed on.
	 * @param player
	 *            The player to land on the field.
	 * @param diceSum
	 *            The dice sum of the player's dice roll
	 */
	public void landOnOwnable(Field field, Player player, int diceSum) {
		Ownable ownable = (Ownable) field;
		// If the field is not owned.
		if (!ownable.isFieldOwned()) {
			boolean bought = GUI.getUserLeftButtonPressed(
					"Du landte på " + ownable.getName() + ", vil du købe grunden?", "Ja", "Nej");
			// Buy the field if the player wants to and he ca afford it.
			if (bought) {
				if (player.buyField(ownable)) {
					GUI.setOwner(player.getPosition(), player.getName());
					GUI.setBalance(player.getName(), player.getAccountBalance());
				} else {
					GUI.getUserButtonPressed("Du har ikke råd til at købe " + ownable.getName(), "Ok");
				}
			}
		}
		// If the field is owned.
		else {
			// If the field is owned by another player, and he is not in prison.
			if (ownable.isFieldOwnedByAnotherPlayer(player) && !ownable.getOwner().getInPrison()) {
				// If the field is a Brewery.
				if (ownable.getType().equals("Tapperi")) {
					Brewery brewery = (Brewery) (ownable);
					brewery.setDiceSum(diceSum);
				}
				int rent = ownable.getRent();
				// Only used for some specific chancecards
				if (doubleRent) {
					rent = rent * 2;
				}
				GUI.getUserButtonPressed(
						"Du landte på " + ownable.getName() + ". Grunden er ejet af " + ownable.getOwner().getName()
								+ ", og du skal betale en leje på " + rent + " kr.",
						"Betal " + rent + " kr. til " + ownable.getOwner().getName());

				// If the player can afford to pay rent.
				if (bankController.playerAffordPayment(player, rent)) {
					player.payRent(ownable.getOwner(), rent);
					GUI.setBalance(ownable.getOwner().getName(), ownable.getOwner().getAccountBalance());
					GUI.setBalance(player.getName(), player.getAccountBalance());
				}
			}
		}
	}

	/**
	 * Method landOnField: Decides what has to be done when a player lands on a
	 * Tax field.
	 * 
	 * @param field
	 *            The field the player landed on.
	 * @param player
	 *            The player to land on the tax field.
	 */
	public void landOnTax(Field field, Player player) {
		int rentAmount = 0;
		Tax tax = (Tax) (field);
		if (tax.getHasTaxRate() == true) {

			// The tax rate rent to be paid.
			int rentTaxRate = (int) (0.1 * player.getFortune());

			// The rent amount to be paid.
			rentAmount = 4000;

			boolean payTaxRate = GUI.getUserLeftButtonPressed("Du skal betale indkomstskat.",
					"Betal 10% (" + rentTaxRate + " kr.)", "Betal 4.000 kr.");
			if (payTaxRate) {
				// Subtract the tax rate rent from the player's balance.
				if (bankController.playerAffordPayment(player, rentTaxRate))
					player.changeAccountBalance(-rentTaxRate);

			} else {
				// Subtract the rent amount from the player's balance.
				if (bankController.playerAffordPayment(player, rentAmount))
					player.changeAccountBalance(-rentAmount);
			}
		} else {
			GUI.getUserButtonPressed("Du skal betale ekstraordinærstatsskat.", "Betal 2.000 kr.");

			// The rent amount to be paid.
			rentAmount = 2000;

			// Subtract the rent amount from the player's balance.
			if (bankController.playerAffordPayment(player, rentAmount))
				player.changeAccountBalance(-rentAmount);
		}
		GUI.setBalance(player.getName(), player.getAccountBalance());
	}

	/**
	 * Method landOnNeutral: Decides what has to be done when a player lands on
	 * the following fields: <br>
	 * - Sent to prison <br>
	 * - Start <br>
	 * - Parking <br>
	 * - Visiting prison. <br>
	 * 
	 * @param player
	 *            The player who landed on the neutral field.
	 */
	public void landOnNeutral(Player player) {
		int prisonFieldNum = 31;
		if (player.getPosition() == prisonFieldNum) {
			prisonController.sendToPrison(player);
		}
	}

	/**
	 * Method landOnChanceField: Decides what has to be done when a player lands
	 * on a chance field.
	 * 
	 * @param player
	 *            The player who landed on the chance field.
	 */
	public void landOnChanceField(Player player) {
		GUI.getUserButtonPressed("Du landte på prøv lykken.", "Træk et kort");
		chanceCardController.draw(player);
	}

	/**
	 * Method setDoubleRent: Sets the value of doubleRent.
	 * 
	 * @param doubleRent
	 *            The value to be set.
	 */
	public void setDoubleRent(boolean doubleRent) {
		this.doubleRent = doubleRent;

	}

	/**
	 * Method TESTgetGameBoard: Used to get the Gameboard in the testmode
	 * controller.
	 * 
	 * @return Gameboard
	 */
	public GameBoard TESTgetGameBoard() {
		return gameBoard;
	}
}
