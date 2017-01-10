package entity;

import entity.field.*;
import data.Reader;

public class GameBoard 
{
	// Instance variables.
	private Field[] fields;
	private int fieldCounter;
	
	/**
	 * Constructor: Constructs a Gameboard from the data file "src/data/Feltliste.txt".
	 */
	public GameBoard(){
		fields = new Field[40];
		fieldCounter = 1;
		Reader reader = new Reader("src/data/Feltliste.txt");
		String[][] fieldData = reader.readFile();
		fieldData = reader.formatFileData(fieldData);
		//oprette felterne
		for(int i = 0; i < fields.length; i++)
		{
			addField(fieldData[i]);
		}
	}

	/**
	 * Method addField: Adds a field to the GameBoard from the information given.
	 * @param information The information given.
	 */
	public void addField(String[] information) {
		String fieldType = information[10];
		switch (fieldType) {
			case "Ejendom":
				addStreet(information);
				break;
			case "Rederi":
				addShipping(information);
				break;
			case "Tapperi":
				addBrewery(information);
				break;	
			case "Chancekort":
				addChance(information);
				break;
			case "Skat":
				addTax(information);
				break;
			case "Start":
			case "Besøg":
			case "Parkering":
			case "Fængsel":
				addNeutral(information);
				break;
			default:
				System.out.println("GameBoard: Not a valid field at field number: " + fieldCounter);
		}
		fieldCounter++;
	}
	
	/**
	 * Method addStreet: Adds a field of the type Street to the GameBoard from the given information.
	 * @param information The information given.
	 */
	private void addStreet(String[] information)
	{
		String name = information[0];
		String type = information[10];
		String description = information[3];
		int price = Integer.parseInt(information[2]);
		String colour = information[1];
		int baseRent = Integer.parseInt(information[3]);
		int housePrice = Integer.parseInt(information[11]);
		int[] houseRent = { Integer.parseInt(information[4]), Integer.parseInt(information[5]), Integer.parseInt(information[6]), Integer.parseInt(information[7]), Integer.parseInt(information[8]) };
		int pledge = Integer.parseInt(information[9]);
		fields[fieldCounter - 1] = new Street(fieldCounter,name, type, description, price, colour, baseRent, housePrice, houseRent, pledge);
	}
	
	/**
	 * Method addShipping: Adds a field of the type Shipping to the GameBoard from the given information.
	 * @param information The information given.
	 */
	private void addShipping(String[] information)
	{
		String name = information[0];
		String type = information[10];
		String description = information[3];
		int price = Integer.parseInt(information[2]);
		fields[fieldCounter - 1] = new Shipping(fieldCounter,name, type, description, price);
	}
	
	/**
	 * Method addBrewery: Adds a field of the type Brewery to the GameBoard from the given information.
	 * @param information The information given.
	 */
	private void addBrewery(String[] information) 
	{
		String name = information[0];
		String type = information[10];
		String description = information[3];
		int price = Integer.parseInt(information[2]);
		fields[fieldCounter - 1] = new Brewery(fieldCounter,name, type, description, price);
	}
	
	/**
	 * Method addChance: Adds a field of the type ChanceField to the GameBoard from the given information.
	 * @param information The given information.
	 */
	private void addChance(String[] information) 
	{
		String name = information[0];
		String type = information[10];
		String description = information[3];
		fields[fieldCounter - 1] = new ChanceField(fieldCounter,name, type, description);
	}
	
	/**
	 * Method addTax: Adds a field of the type TaxField to the GameBoard from the given information.
	 * @param information The given information.
	 */
	private void addTax(String[] information) 
	{
		String name = information[0];
		String type = information[10];
		String description = "";
		boolean rate = Boolean.parseBoolean(information[5]);
		int amount = Integer.parseInt(information[4]);
		fields[fieldCounter - 1] = new Tax(fieldCounter,name, type, description, rate, amount);
	}
	
	/**
	 * Method addNeutral: Adds a field of the type Neutral to the GameBoard from the given information.
	 * @param information The given information.
	 */
	private void addNeutral(String[] information) 
	{
		String name = information[0];
		String type = information[10];
		String description = "";
		fields[fieldCounter - 1] = new Neutral(fieldCounter,name, description, type);
	}
		
	/**
	 * Method getField: Returns a field from the GameBoard.
	 * @param fieldNum The number of the field you want to be returned.
	 * @return The field.
	 */
	public Field getField(int fieldNum)
	{
		return fields[fieldNum-1];	
	}
}
