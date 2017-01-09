package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import entity.chanceCard.*;

public class ChanceCardDeck {
	private int num = 0;

	private ChanceCard[] chanceCardDeck;

	/**
	 * Object ChanceCardDeck creates a deck of Chance cards.
	 * 
	 */
	public ChanceCardDeck() {

		chanceCardDeck = new ChanceCard[39];
		int cardNumber = 0;

		//Imports file
		String fileName = "data.csv";
		File file = new File(fileName);
		
		//Scans the file and splits it up by commas
		try 
		{
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNextLine())
			{
				String data = inputStream.nextLine();
				String[] fromInfo = data.split(",");
				
				switch (fromInfo[0])
				{
				case "Grant": chanceCardDeck[cardNumber] = createGrant(fromInfo);
					break;
				case "Party": chanceCardDeck[cardNumber] = createParty(fromInfo);
					break;
				case "Payment": chanceCardDeck[cardNumber] = createPayment(fromInfo);
					break;
				case "Prison": chanceCardDeck[cardNumber] = createPrison(fromInfo);
					break;
				case "TaxCard": chanceCardDeck[cardNumber] = createTaxCard(fromInfo);
					break;
				case "MoveToNearestShipping": chanceCardDeck[cardNumber] = createMoveToNearestShipping(fromInfo);
					break;
				case "MoveToPrison": chanceCardDeck[cardNumber] = createMoveToPrison(fromInfo);
					break;
				case "MoveToField": chanceCardDeck[cardNumber] = createMoveToField(fromInfo);
					break;
				case "MoveThreeSteps": chanceCardDeck[cardNumber] = createMoveThreeSteps(fromInfo);
				}
<<<<<<< HEAD
				cardNumber++;
=======
				//If the type is a Movement card, then it saves it in the chanceCards[]
				if(values[0].equals("Movement")){
					chanceCards[card] = new Movement(values[0],values[1],Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					card++;
				}
				//If the type is a Party card, then it saves it in the chanceCards[]
				if(values[0]=="Party"){
					chanceCards[card] = new Party(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				//If the type is a Payment card, then it saves it in the chanceCards[]
				if(values[0]=="Payment"){
					chanceCards[card] = new Payment(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				//If the type is a Prison card, then it saves it in the chanceCards[]
				if(values[0]=="Prison"){
					chanceCards[card] = new Prison(values[0],values[1],Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					card++;
				}
				//If the type is a TaxCard card, then it saves it in the chanceCards[]
				if(values[0]=="TaxCard"){
					chanceCards[card] = new TaxCard(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				


>>>>>>> refs/heads/Develop
			}
			shuffleDeck();
			inputStream.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method draw: Returns the first card in the deck 
	 * and afterwards puts it in the bottom of the deck
	 * @return The first card of the deck.
	 */
	public ChanceCard draw()
	{	
		ChanceCard first = chanceCardDeck[0];
		
		for (int i = 0; i < chanceCardDeck.length - 1; i++)
		{
			chanceCardDeck[i] = chanceCardDeck[i+1];
		}
		
		chanceCardDeck[chanceCardDeck.length-1] = first;
		return first;
	}
	
	/**
	 * Method shuffle: Shuffles the chance card deck.
	 */
	public void shuffle()
	{
		for (int i = 0; i < 10000; i++)
		{
			swapTwoCards();
		}
	}
	
	/**
	 * Method swapTwoCards: Swaps two chance cards in the deck.
	 */
	private void swapTwoCards()
	{
		Random generator = new Random();
		int cardNumber1 = generator.nextInt(chanceCardDeck.length);
		ChanceCard temp = chanceCardDeck[cardNumber1];
		int cardNumber2 = generator.nextInt(chanceCardDeck.length);
		chanceCardDeck[cardNumber1] = chanceCardDeck[cardNumber2];
		chanceCardDeck[cardNumber2] = temp;
	}
	
	/**
	 * Method creatGrant: Returns a Grant chanceCard from the given information.
	 * @param information The information given.
	 * @return The Grant chanceCard.
	 */
	private Grant createGrant(String[] i)
	{
		Grant grant = new Grant(i[0],i[1],toInt(i[2]));
		return grant;
	}
	
	private TaxCard createTaxCard(String[] i)
	{
		TaxCard taxCard = new TaxCard(i[0],i[1],toInt(i[2]));
		return taxCard;
	}
	
	private Payment createPayment(String[] i)
	{
		Payment payment = new Payment(i[0],i[1],toInt(i[2]));
		return payment;
	}
	
	private Party createParty(String[] i)
	{
		Party party = new Party(i[0],i[1],toInt(i[2]));
		return party;
	}
	
	private Prison createPrison(String[] i)
	{
		Prison prison = new Prison(i[0],i[1],toBoolean(i[7]), toBoolean(i[8]));
		return prison;
	}
	
	private MoveThreeSteps createMoveThreeSteps(String[] i)
	{
		MoveThreeSteps moveThreeSteps = new MoveThreeSteps(i[0], i[1], toInt(i[2]));
		return moveThreeSteps;
	}
	
	private MoveToField createMoveToField(String[] i)
	{
		MoveToField moveToField = new MoveToField(i[0], i[1], toInt(i[2]));
		return moveToField;
	}
	
	private MoveToPrison createMoveToPrison(String[] i)
	{
		MoveToPrison moveToPrison = new MoveToPrison(i[0],i[1]);
		return moveToPrison;
	}
	
	private MoveToNearestShipping createMoveToNearestShipping(String[] i)
	{
		String[] stringArray = {i[2], i[3], i[4], i[5]};
		MoveToNearestShipping moveToNearestShipping = new MoveToNearestShipping(i[0], i[1], toIntArray(stringArray), toBoolean(i[6]));
		return moveToNearestShipping;
	}
	
	private int toInt(String s)
	{
		return Integer.parseInt(s);
	}
	
	private boolean toBoolean(String s)
	{
		return Boolean.parseBoolean(s);
	}
	
	private int[] toIntArray(String... s)
	{
		int[] intArray = new int[s.length];
		for(int i = 0; i < s.length; i++)
		{
			intArray[i] = toInt(s[i]);
		}
		return intArray;
	}
}
