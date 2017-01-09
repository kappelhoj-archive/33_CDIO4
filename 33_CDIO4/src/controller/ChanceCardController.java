package controller;

import entity.ChanceCardDeck;
import entity.Player;
import entity.chanceCard.*;
import controller.PrisonController;
import desktop_fields.Tax;
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
	 * The method draw draws a card from a newly generated chancecarddeck.
	 * Depending on what type of chancecard is drawn, the draw method invokes
	 * different methods. The method uses a switch to differ between the
	 * individual types of chancecard.
	 * 
	 * @param player
	 *            The player who's "drawing" a card.
	 */
	public void draw(Player player)

	{
		ChanceCard currentCard = deck.draw();

		String type = currentCard.getType();
		System.out.println(type);
		System.out.println(currentCard.getDescription());
		GUI.displayChanceCard(currentCard.getDescription());

		switch (type) { 
		case "Grant":
			drawGrant(currentCard, player);
			break;
		// case "Party": drawParty(currentCard, player);
		// break;
		case "Payment": drawPayment(currentCard, player);
		 break;
		// case "Prison": drawPrison(currentCard, player);
		// break;
		// case "Tax": drawTaxCard(currentCard, player);
		// break;
		case "MoveToNearestShipping":
			drawMoveToNearestShipping(currentCard, player);
			break;
		case "MoveToPrison":
			drawMoveToPrison(currentCard, player);
			break;
		case "MoveToField":
			drawMoveToField(currentCard, player);
			break;
		case "MoveThreeSteps":
			drawMoveThreeSteps(currentCard, player);
			break;
		}
	}

	private void drawMoveToNearestShipping(ChanceCard currentCard, Player player) {
		MoveToNearestShipping card = (MoveToNearestShipping) currentCard;
		int[] shippingPos = card.getShippingPositions();

		player.setPosition(shippingPos[0]);
		for (int i = 0; i < shippingPos.length; i++)
			if (shippingPos[i] > player.getPosition()) {
				player.setPosition(shippingPos[i]);
				break;
			}
		main.getLandOnFieldController().setDoubleRent(card.getDoubleRent());
		
		main.movePlayerOnGUI();
		main.getLandOnFieldController().landOnField(player, 2);
	}

	private void drawMoveToPrison(ChanceCard currentCard, Player player) {
		prison.sendToPrison(player);
	}

	private void drawMoveToField(ChanceCard currentCard, Player player) {
		player.setPosition(((MoveToField) currentCard).getMoveTo());
		main.movePlayerOnGUI();
		main.getLandOnFieldController().landOnField(player, 2);

	}

	private void drawGrant(ChanceCard currentCard, Player player) {
		Grant grant = (Grant) currentCard;
		if (player.getFortune() <= 15000) {
			player.changeAccountBalance(grant.getAmount());
			GUI.setBalance(player.getName(), player.getAccountBalance());
		}
	}

	private void drawMoveThreeSteps(ChanceCard currentCard, Player player) {
		MoveThreeSteps move = (MoveThreeSteps) currentCard;
		main.movePlayer(move.getSteps());
		main.getLandOnFieldController().landOnField(player, main.rollDice());
		main.playerTurnDecision();
	}
	
	private void drawPayment(ChanceCard currentCard, Player player){
		player.changeAccountBalance(((Payment)currentCard).getAmount());
		GUI.setBalance(player.getName(), player.getAccountBalance());
	}

}
