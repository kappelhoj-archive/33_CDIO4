package controller;
import entity.ChanceCardDeck;
import entity.ChanceCard.ChanceCard;
import entity.ChanceCard.Movement;


public class ChanceCardController {

	ChanceCardDeck deck;
	
	ChanceCardController()
	{
		deck = new ChanceCardDeck();
		
		
		
	}
	public void draw(){
		ChanceCard currentCard=deck.drawCard();
		String type = currentCard.getType();
		switch(type)
		{
		case "Grant": drawGrant();
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
	public void drawGrant()
	{
		
	}
	public void drawMovement(ChanceCard currentCard)
	{
		Movement card = ((Movement)currentCard);

		if(card.getMove()==0 & card.getField1()!=0 & card.getField2()==0)
		{
			movePlayerSpecificField();	
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
	public void movePlayerSpecificField()
	{
		
	}
	
	
}
