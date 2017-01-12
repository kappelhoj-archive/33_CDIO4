package controller;

import entity.ChanceCardDeck;
import entity.Player;
import entity.chanceCard.*;
import controller.PrisonController;
import desktop_resources.GUI;
import controller.BankController;

/**
 * This class is responsible for handling the chance cards when the player draws them..
 * @author Gruppe33
 *
 */
public class ChanceCardController {

	private ChanceCardDeck deck;
	private PrisonController prison;
	private BankController bank;
	private MainController main;

	ChanceCardController(PrisonController prison, BankController bank, MainController main) {
		deck = new ChanceCardDeck();
		this.prison = prison;
		this.bank = bank;
		this.main = main;

	}


	/**
	 * Method draw: Decides what happens when a player draws a card from the chance card deck.
	 * @param player The player who draws a chance card.
	 */
	public void draw(Player player)

	{
		ChanceCard currentCard = deck.draw();

		String type = currentCard.getType();
		GUI.displayChanceCard(currentCard.getDescription());
		GUI.getUserButtonPressed("Du har trukket et Prøv-lykken kort \n" + currentCard.getDescription(),"Ok");
		switch (type) { 
		case "Grant": drawGrant(currentCard, player);
			break;
		case "Payment": drawPayment(currentCard, player);
			break;
		case "MoveToNearestShipping": drawMoveToNearestShipping(currentCard, player);
			break;
		case "MoveToPrison": drawMoveToPrison(currentCard, player);
			break;
		case "MoveToField": drawMoveToField(currentCard, player);
			break;
		case "MoveThreeSteps": drawMoveThreeSteps(currentCard, player);
			break;
		// case "Prison": drawPrison(currentCard, player);
			// break;
		// case "Tax": drawTaxCard(currentCard, player);
			// break;
		// case "Party": drawParty(currentCard, player);
			// break;
		}
	}

	/**
	 * Method DrawMoveToNearestShipping: Decides what happens when a 
	 * player draws a chance card of the type 'move to nearest shipping'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawMoveToNearestShipping(ChanceCard currentCard, Player player) 
	{
		MoveToNearestShipping card = (MoveToNearestShipping) currentCard;
		int[] shippingPos = card.getShippingPositions();
		int currentPos = player.getPosition();
		
		// Sets the position of the player to the first shipping field if you are passed the last shipping field.
		player.setPosition(shippingPos[0]);
		// Sets the position of the player to the next shipping field if you haven't passed the last shipping field.
		for (int i = 0; i < shippingPos.length; i++)
		{
			if (shippingPos[i] > currentPos) 
			{
				player.setPosition(shippingPos[i]);
				break;
			}
		}
		main.getLandOnFieldController().setDoubleRent(card.getDoubleRent());
		main.movePlayerOnGUI();
		//Hvis start passeres.
		if (currentPos > player.getPosition())
		{
			main.givePlayer4000();
		}
		main.getLandOnFieldController().landOnField(player, 0);
	}

	/**
	 * Method drawMoveToPrison: Decides what happens when a player draws a 
	 * chance card of the type 'move to prison'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawMoveToPrison(ChanceCard currentCard, Player player) 
	{
		prison.sendToPrison(player);
	}

	/**
	 * Method drawMoveToField: Decides what happens when a player draws a 
	 * chance card of the type 'move to field'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawMoveToField(ChanceCard currentCard, Player player) 
	{
		main.movePlayerTo(((MoveToField) currentCard).getMoveTo());
		main.getLandOnFieldController().landOnField(player, 0);

	}

	/**
	 * Method drawGrant: Decides what happens when a player draws a 
	 * chance card of the type 'grant'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawGrant(ChanceCard currentCard, Player player) 
	{
		Grant grant = (Grant) currentCard;
		if (player.getFortune() <= 15000) {
			player.changeAccountBalance(grant.getAmount());
			GUI.setBalance(player.getName(), player.getAccountBalance());
		}
	}

	/**
	 * Method drawMoveThreeSteps: Decides what happens when a player draws a
	 * chance card of the type 'move three steps'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawMoveThreeSteps(ChanceCard currentCard, Player player) 
	{
		MoveThreeSteps move = (MoveThreeSteps) currentCard;
		main.movePlayer(move.getSteps());
		main.getLandOnFieldController().landOnField(player, 0);
	}
	
	/**
	 * Method drawPayment: Decides what happens when a player draws a
	 * chance card of the type 'payment'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawPayment(ChanceCard currentCard, Player player){
		
		if (bank.playerAffordPayment(player, ((Payment)currentCard).getAmount()))
		{
		player.changeAccountBalance(((Payment)currentCard).getAmount());
		GUI.setBalance(player.getName(), player.getAccountBalance());
		}
	}

}
