package controller;

import entity.ChanceCardDeck;
import entity.Player;
import entity.chanceCard.*;
import controller.PrisonController;
import desktop_resources.GUI;
import controller.BankController;

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

		player.setPosition(shippingPos[0]);
		for (int i = 0; i < shippingPos.length; i++)
		{
			if (shippingPos[i] > player.getPosition()) 
			{
				player.setPosition(shippingPos[i]);
				break;
			}
		}
		main.getLandOnFieldController().setDoubleRent(card.getDoubleRent());
		main.movePlayerOnGUI();
		main.getLandOnFieldController().landOnField(player, 2);
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
		player.setPosition(((MoveToField) currentCard).getMoveTo());
		main.movePlayerOnGUI();
		main.getLandOnFieldController().landOnField(player, 2);

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
		main.getLandOnFieldController().landOnField(player, main.rollDice());
		main.playerTurnDecision();
	}
	
	/**
	 * Method drawPayment: Decides what happens when a player draws a
	 * chance card of the type 'payment'.
	 * @param currentCard The card drawn.
	 * @param player The player who draws the chance card.
	 */
	private void drawPayment(ChanceCard currentCard, Player player){
		player.changeAccountBalance(((Payment)currentCard).getAmount());
		GUI.setBalance(player.getName(), player.getAccountBalance());
	}

}
