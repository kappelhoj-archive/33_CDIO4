package controller;
import entity.ChanceCardDeck;
import entity.Player;
import entity.chanceCard.*;
import controller.PrisonController;
import desktop_fields.Tax;
import desktop_resources.GUI;
import controller.BankController;


public class ChanceCardController {

	ChanceCardDeck deck;
	PrisonController prison;
	BankController bank;
	MainController main;
	
	ChanceCardController(PrisonController prison,BankController bank, MainController main)
	{
		deck = new ChanceCardDeck();
		this.prison = prison;
		this.bank = bank;
		this.main = main;

	}
	/**
	 * The method draw draws a card from a newly generated chancecarddeck.
	 * Depending on what type of chancecard is drawn, the draw method invokes different
	 * methods. The method uses a switch to differ between the individual types of chancecard.
	 * @param player The player who's "drawing" a card.
	 */
	public void draw(Player player, Player[] borrowers)

	{	
		ChanceCard currentCard = deck.draw();
		
		String type = currentCard.getType();
		
		GUI.displayChanceCard(currentCard.getDescription());
		
		switch(type)
		{
		case "Grant": drawGrant(currentCard, player);
			break;
		case "Party": drawParty(currentCard, player);
			break;
		case "Payment": drawPayment(currentCard, player);
			break;
		case "Prison": drawPrison(currentCard, player);
			break;
		case "Tax": drawTaxCard(currentCard, player);
			break;	
		case "MoveThreeSteps": drawMoveThreeSteps(currentCard, player);
			break;
		case "MoveToField": drawMoveToField(currentCard, player);
			break;
		case "MoveToNearestShipping": drawMoveToNearestShipping(currentCard, player);
			break;
		case "MoveToPrison": drawMoveToPrison(currentCard, player);
			break;
		}	
	}
	
	private void drawGrant(ChanceCard currentCard, Player player)
	{
		Grant grant = (Grant) currentCard;
		if (player.getFortune() <= 15000)
		{
			player.changeAccountBalance(grant.getAmount());
		}
	}
	
	private void drawMoveThreeSteps(ChanceCard currentCard, Player player)
	{
		MoveThreeSteps move = (MoveThreeSteps) currentCard;
		main.movePlayer(move.getSteps());
		main.getLandOnFieldController().landOnField(player, move.getSteps());
		main.playerTurnDecision();
	}
	
}
