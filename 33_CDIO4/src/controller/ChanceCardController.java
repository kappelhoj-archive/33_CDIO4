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
	public void draw(Player player)

	{	
		ChanceCard currentCard = deck.draw();
		
		String type = currentCard.getType();
		
		GUI.displayChanceCard(currentCard.getDescription());
		
		switch(type)
		{
		case "Grant": drawGrant(currentCard, player);
			break;
//		case "Party": drawParty(currentCard, player);
//			break;
//		case "Payment": drawPayment(currentCard, player);
//			break;
//		case "Prison": drawPrison(currentCard, player);
//			break;
//		case "Tax": drawTaxCard(currentCard, player);
//			break;	
		case "MoveThreeSteps": drawMoveThreeSteps(currentCard, player);
			break;
//		case "MoveToField": drawMoveToField(currentCard, player);
//			break;
//		case "MoveToNearestShipping": drawMoveToNearestShipping(currentCard, player);
//			break;
//		case "MoveToPrison": drawMoveToPrison(currentCard, player);
//			break;
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
	
<<<<<<< HEAD
=======
	/**
	 * The method drawPrison is invoked if the drawn card is of the type Prison.
	 * @param currentCard The currently drawn card.
	 */
	public void drawPrison(ChanceCard currentCard)
	{
		Prison card = ((Prison)currentCard);
	}
	

	/**
	 * The method drawTaxCard is invoked if the drawn card is of the type TaxCard.
	 * @param currentCard The currently drawn card.
	 */
	public void drawTaxCard(ChanceCard currentCard)
	{
		TaxCard card = ((TaxCard)currentCard);
	}

	/**
	 * The method movePlayerPrison sends the player to prison.
	 */
	public void movePlayerPrison()
	{
		prison.sentToPrison(tempPlayer);
	}
	/**
	 * The method movePlayerSpecificField moves the player to a specific field.
	 * @param moveToField The field the player is moved to.
	 */
	public void movePlayerSpecificField(int moveToField)
	{
		tempPlayer.setPosition(moveToField);
	}
	
	public void movePlayer(int move)
	{
		tempPlayer.setPosition(tempPlayer.getPosition() + move);
	}
	/**
	 * The method movePlayerSeveralSpecificField is capable of moving a player to a number of specified fields.
	 * This is needed for when a player has to move to the nearest shipping company
	 * @param field1 Specific ownable field 1.
	 * @param field2 Specific ownable field 2.
	 * @param field3 Specific ownable field 3.
	 * @param field4 Specific ownable field 4.
	 * @param rent checks if the rent has to be double
	 */
	public void movePlayerSeveralSpecificField(int field1, int field2, int field3, int field4, boolean rent)
	{
		int[] fields = {tempPlayer.getPosition()-field1,
						tempPlayer.getPosition()-field2,
						tempPlayer.getPosition()-field3,
						tempPlayer.getPosition()-field4};
		
		int temp = 15;
		for(int i = 0; i < fields.length ; i++)
		{
			if(fields[i]>0)
			{
				temp = Math.min(fields[i], temp);
			}
		}
		//if(rent==true){tempPlayer.} Der skal laves double rent hvis true!!!!!!!!!!!!!!!!!!!!!!!!!!!
		tempPlayer.setPosition(temp);
	}


>>>>>>> refs/heads/Develop
}
