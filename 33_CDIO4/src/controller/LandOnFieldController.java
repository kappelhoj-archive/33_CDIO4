package controller;

import desktop_resources.GUI;
import entity.Player;
import entity.field.*;
import entity.GameBoard;

public class LandOnFieldController {

	// Instance variables
	private PrisonController prisonController;
	private ChanceCardController chanceCardController;
	private BankController bankController;
	private GameBoard gameBoard;

	/**
	 * FieldController constructor.
	 * 
	 * @param prisonController
	 *            PrisonController
	 * @param mainController
	 *            MainController.
	 */
	public LandOnFieldController(PrisonController prisonController, MainController mainController) {
		bankController = new BankController();
		this.prisonController = new PrisonController(mainController);
		this.chanceCardController = new ChanceCardController(prisonController, bankController);
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
		if (!ownable.isFieldOwned()) {
			boolean bought = GUI.getUserLeftButtonPressed(
					"Du landte på " + ownable.getName() + ", vil du købe grunden?", "Ja", "Nej");
			if (bought) {
				if (player.buyField(ownable)) {
					GUI.setOwner(player.getPosition(), player.getName());
					GUI.setBalance(player.getName(), player.getAccountBalance());
				} else {
					GUI.getUserButtonPressed("Du har ikke råd til at købe " + ownable.getName(), "Ok");
				}
			}
		} else {
			if (ownable.isFieldOwnedByAnotherPlayer(player)) {
				if (ownable.getType().equals("Brewery")) {
					Brewery brewery = (Brewery) (ownable);
					brewery.setDiceSum(diceSum);
				}
				GUI.getUserButtonPressed(
						"Du skal betale " + ownable.getRent() + " til " + ownable.getOwner().getName() + ".", "Ok");
				player.payRent(ownable.getOwner(), ownable.getRent());
				GUI.setBalance(ownable.getOwner().getName(), ownable.getOwner().getAccountBalance());
				GUI.setBalance(player.getName(), player.getAccountBalance());
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
				player.changeAccountBalance(-rentTaxRate);
			} else {
				// Subtract the rent amount from the player's balance.
				player.changeAccountBalance(-rentAmount);
			}
		} else {
			GUI.getUserButtonPressed("Du skal betale ekstraordinærstatsskat.", "Betal 2.000 kr.");
			
			// The rent amount to be paid.
			rentAmount = 2000; // The rent to be paid.
			
			// Subtract the rent amount from the player's balance.
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

	public GameBoard TESTgetGameBoard() {
		return gameBoard;
	}
}
