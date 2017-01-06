package controller;
import entity.ChanceCardDeck;
import entity.Player;
import controller.PrisonController;
import desktop_fields.Tax;
import entity.ChanceCard.chanceCard;
import entity.ChanceCard.Grant;
import entity.ChanceCard.Movement;
import entity.ChanceCard.Party;
import entity.ChanceCard.Payment;
import entity.ChanceCard.Prison;
import entity.ChanceCard.TaxCard;
import desktop_resources.GUI;
import controller.BankController;


public class ChanceCardController {

	ChanceCardDeck deck;
	Player tempPlayer;
	PrisonController prison;
	BankController bank;
	
	ChanceCardController(PrisonController prison,BankController bank)
	{
		deck = new ChanceCardDeck();
		this.prison = prison;
		this.bank = bank;

	}
	/**
	 * The method draw draws a card from a newly generated chancecarddeck.
	 * Depending on what type of chancecard is drawn, the draw method invokes different
	 * methods. The method uses a switch to differ between the individual types of chancecard.
	 * @param player The player who's "drawing" a card.
	 */
	public void draw(Player player)

	{
		tempPlayer= player;
		
		chanceCard currentCard=deck.drawCard();
		
		String type = currentCard.getType();
		
		GUI.displayChanceCard(currentCard.getDesc());
		
		switch(type)
		{
		case "Grant": drawGrant(currentCard);
		break;
		case "Movement": drawMovement(currentCard);
		break;
		case "Party": drawParty(currentCard);
		break;
		case "Payment": drawPayment(currentCard);
		break;
		case "Prison": drawPrison(currentCard);
		break;
		case "Tax": drawTaxCard(currentCard);
		break;		
		}	
	}
	
	/**
	 * The method drawGrant is invoked if the drawn card is of the type Grant.
	 * @param currentCard The currently drawn card.
	 */
	public void drawGrant(chanceCard currentCard)
	{
	Grant card = ((Grant)currentCard);
	bank.chancePaymentChecker(card.getAmount(), tempPlayer);
	}
	

	/**
	 * The method drawMovement is invoked if the drawn card is of the type Movement.
	 * The method moves the player a specific distance, or to a specific field.
	 * @param currentCard The currently drawn card.
	 */
	public void drawMovement(chanceCard currentCard)
	{
		Movement card = ((Movement)currentCard);

		//if the card wants to move a player to a specific field.
		if(card.getMove()==0 & card.getField1()!=0 & card.getField2()==0)
		{
			movePlayerSpecificField(card.getField1());	
		}

		//if the card wants to move the player to the closest specific field of several.
		if(card.getMove()==0 & card.getField1()!=0 & card.getField2()!=0)
		{
			movePlayerSeveralSpecificField(card.getField1(),card.getField2(),card.getField3(),card.getField4(),card.getDoubleRent());
		}

		//if the card wants to move a player forwards or backwards
		if(card.getMove()!=0)
		{
			movePlayer(card.getMove());
		}
		
		//prison card
		if(card.getInprisoned())
		{
			movePlayerPrison();
		}

	}

	/**
	 * The method drawParty is invoked if the drawn card is of the type Party.
	 * @param currentCard The currently drawn card.
	 */
	public void drawParty(chanceCard currentCard)
	{
		Party card = ((Party)currentCard);
	}

	/**
	 * The method drawPayment is invoked if the drawn card is of the type Payment.
	 * @param currentCard The currently drawn card.
	 */
	public void drawPayment(chanceCard currentCard)
	{
		Payment card = ((Payment)currentCard);
	}
	
	/**
	 * The method drawPrison is invoked if the drawn card is of the type Prison.
	 * @param currentCard The currently drawn card.
	 */
	public void drawPrison(chanceCard currentCard)
	{
		Prison card = ((Prison)currentCard);
	}
	

	/**
	 * The method drawTaxCard is invoked if the drawn card is of the type TaxCard.
	 * @param currentCard The currently drawn card.
	 */
	public void drawTaxCard(chanceCard currentCard)
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
	 * @param rent The rent to be payed to the owner of the shipping company.
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
		movePlayer(temp);
	}


}
