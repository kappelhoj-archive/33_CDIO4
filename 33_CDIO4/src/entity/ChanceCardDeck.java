package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import entity.ChanceCard.*;
import kortspil.Kort;

public class ChanceCardDeck {
	private int num = 0;

	private chanceCard[] chanceCardDeck;

	/**
	 * Object ChanceCardDeck creates a deck of Chance cards.
	 * 
	 */
	public ChanceCardDeck() {


		chanceCardDeck = new chanceCard[39];
		int card = 0;

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
				String[] values = data.split(",");
				
				//If the type is a Grant card, then it saves it in the chanceCards[]
				if(values[0]=="Grant"){
					chanceCardDeck[card] = new Grant(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				//If the type is a Movement card, then it saves it in the chanceCards[]
				if(values[0].equals("Movement")){
					chanceCardDeck[card] = new Movement(values[0],values[1],Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					card++;
				}
				//If the type is a Party card, then it saves it in the chanceCards[]
				if(values[0]=="Party"){
					chanceCardDeck[card] = new Party(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				//If the type is a Payment card, then it saves it in the chanceCards[]
				if(values[0]=="Payment"){
					chanceCardDeck[card] = new Payment(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}
				//If the type is a Prison card, then it saves it in the chanceCards[]
				if(values[0]=="Prison"){
					chanceCardDeck[card] = new Prison(values[0],values[1],Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					card++;
				}
				//If the type is a TaxCard card, then it saves it in the chanceCards[]
				if(values[0]=="TaxCard"){
					chanceCardDeck[card] = new TaxCard(values[0],values[1],Integer.parseInt(values[2]));
					card++;
				}


			}
			inputStream.close();


		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Method draw: Returns the first card in the deck 
	 * and afterwards puts it in the bottom of the deck
	 * @return The first card of the deck.
	 */
	public chanceCard draw()
	{	
		chanceCard first = chanceCardDeck[0];
		
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
		chanceCard temp = chanceCardDeck[cardNumber1];
		int cardNumber2 = generator.nextInt(chanceCardDeck.length);
		chanceCardDeck[cardNumber1] = chanceCardDeck[cardNumber2];
		chanceCardDeck[cardNumber2] = temp;
	}

}
