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
				String[] values = data.split(",");
				
				//Lav små metoder der laver hver af de forskellige chanceCards.
				switch (values[0])
				{
				case "Grant": chanceCardDeck[cardNumber] = new Grant(values[0],values[1],Integer.parseInt(values[2]));
					break;
				//case "Movement": chanceCardDeck[cardNumber] = new Movement(values[0],values[1],Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					break;
				case "Party": chanceCardDeck[cardNumber] = new Party(values[0],values[1],Integer.parseInt(values[2]));
					break;
				case "Payment": chanceCardDeck[cardNumber] = new Payment(values[0],values[1],Integer.parseInt(values[2]));
					break;
				case "Prison": chanceCardDeck[cardNumber] = new Prison(values[0],values[1],Boolean.parseBoolean(values[7]),Boolean.parseBoolean(values[8]));
					break;
				case "TaxCard": chanceCardDeck[cardNumber] = new TaxCard(values[0],values[1],Integer.parseInt(values[2]));
					break;
				}
				cardNumber++;
			}
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

}
