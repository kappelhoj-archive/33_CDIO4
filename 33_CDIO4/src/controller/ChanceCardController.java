package controller;
import entity.ChanceCardDeck;
import entity.Player;
import controller.PrisonController;
import desktop_fields.Tax;
import entity.ChanceCard.ChanceCard;
import entity.ChanceCard.Grant;
import entity.ChanceCard.Movement;
import entity.ChanceCard.Party;
import entity.ChanceCard.Payment;
import entity.ChanceCard.Prison;
import entity.ChanceCard.TaxCard;
import desktop_resources.GUI;
import controller.Bank;


public class ChanceCardController {

	ChanceCardDeck deck;
	Player tempPlayer;
	PrisonController prison;
	Bank bank;
	
	ChanceCardController(PrisonController prison,Bank bank)
	{
		deck = new ChanceCardDeck();
		this.prison = prison;
		this.bank = bank;

	}
	
	public void draw(Player player)

	{
		tempPlayer= player;
		
		ChanceCard currentCard=deck.drawCard();
		
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
	
	public void drawGrant(ChanceCard currentCard)
	{
	Grant card = ((Grant)currentCard);
	bank.chancePaymentChecker(card.getAmount(), tempPlayer);
	}
	
	public void drawMovement(ChanceCard currentCard)
	{
		Movement card = ((Movement)currentCard);

		//if the card wants to move a player to a specific field
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
		if(card.getInprisoned()==true)
		{
			movePlayerPrison();
		}

	}
	
	public void drawParty(ChanceCard currentCard)
	{
		Party card = ((Party)currentCard);
	}
	
	public void drawPayment(ChanceCard currentCard)
	{
		Payment card = ((Payment)currentCard);
	}
	
	public void drawPrison(ChanceCard currentCard)
	{
		Prison card = ((Prison)currentCard);
	}
	
	public void drawTaxCard(ChanceCard currentCard)
	{
		TaxCard card = ((TaxCard)currentCard);
	}
	
	public void movePlayerPrison()//ER DETTE RIGTIGT ?????????????????????????????????????? og skal denne metode rykke ham dertil ?
	{
		prison.sentToPrison(tempPlayer);
	}
	
	public void movePlayerSpecificField(int moveToField)
	{
		tempPlayer.setPosition(moveToField);
	}
	public void movePlayer(int move)
	{
		tempPlayer.setPosition(tempPlayer.getPosition() + move);
	}
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
