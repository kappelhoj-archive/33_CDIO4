package controller;
import entity.ChanceCardDeck;
import entity.Player;
import entity.ChanceCard.ChanceCard;
import entity.ChanceCard.Movement;
import entity.ChanceCard.Payment;


public class ChanceCardController {

	ChanceCardDeck deck;
	Player tempPlayer;

	ChanceCardController()
	{
		deck = new ChanceCardDeck();



	}
	public void draw(Player player)
	{
		tempPlayer= player;
		
		ChanceCard currentCard=deck.drawCard();
		
		String type = currentCard.getType();
		
		switch(type)
		{
		case "Grant": drawGrant(currentCard);
		break;
		case "Movement": drawMovement(currentCard);
		break;
		case "Party": drawParty();
		break;
		case "Payment": drawPayment();
		break;
		case "Prison": drawPrison();
		break;
		case "Tax": drawTax();
		break;		
		}


	}
	public void drawGrant(ChanceCard currentCard)
	{
		Payment card = ((Payment)currentCard);
		tempPlayer.

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
	public void drawParty()
	{

	}
	public void drawPayment()
	{

	}
	public void drawPrison()
	{

	}
	public void drawTax()
	{

	}
	public void movePlayerPrison()//ER DETTE RIGTIGT ?????????????????????????????????????? og skal denne metode rykke ham dertil ?
	{
		tempPlayer.setPrison(true);
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
