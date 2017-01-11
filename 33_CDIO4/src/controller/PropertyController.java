package controller;

import desktop_resources.GUI;
import entity.Player;
import controller.MainController;
import entity.field.*;

public class PropertyController {

	// Instance variables
	private int houses;
	private int hotels;

	/**
	 * Constructor: Constructs a PropertyController.
	 */
	public PropertyController() {
		this.houses = 32;
		this.hotels = 12;
	}

	/**
	 * Method getHouses: Returns the number of houses available.
	 * 
	 * @return Amount of houses available.
	 */
	public int getHouses() {
		return houses;
	}

	/**
	 * Method getHotels: Returns the number of hotels available.
	 * 
	 * @return Amount of hotels available.
	 */
	public int getHotels() {
		return hotels;
	}

	/**
	 * Method changeHouses: Changes the amount of houses available.
	 * 
	 * @param amount
	 *            The amount to change the houses available with.
	 */
	public void changeHouses(int amount) {
		houses = houses + amount;
	}

	/**
	 * Method changeHotels: Changes the amount of hotels available.
	 * 
	 * @param amount
	 *            The amount to change the hotels available with.
	 */
	public void changeHotels(int amount) {
		hotels = hotels + amount;
	}

	/**
	 * Method countStreetColours: Returns the amount of each
	 * coloured streets the player owns as a integer array.
	 * @param player The player who owns the fields.
	 * @return int array containing the information. fx int[0] = amount of blue coloured streets owned.
	 */
	private int[] countStreetColours(Player player) {
		int[] countedStreetColours = new int[8];
		String[] streetColours = {"Blå", "Orange", "Grøn", "Grå", "Rød", "Hvid", "Gul", "Lilla"};
		
		for(int i = 0; i < countedStreetColours.length; i++)
		{
			countedStreetColours[i] = player.getStreetsOwned(streetColours[i]);
		}
		return countedStreetColours;
	}

	/**
	 * Method canBuildOnColour: Returns which street colours you can build at represented as a boolean array. 
	 * @param player The player who owns the streets.
	 * @return The boolean array which contains which streets you can buy buildings at.
	 */
	private boolean[] canBuildOnColour(Player player) {
		// required amount of streets with the same colours to build a house.
		int[] requiredStreets = { 2, 3, 3, 3, 3, 3, 3, 2 };
		boolean[] canBuildOnColour = new boolean[8];

		for (int i = 0; i < canBuildOnColour.length; i++) {
			canBuildOnColour[i] = countStreetColours(player)[i] == requiredStreets[i];
		}
		return canBuildOnColour;

	}

	/**
	 * Method canBuildOnColourString: Returns which street colours you can buy buildings at.
	 * @param player The player that wants to buy a house.
	 * @return The String array which contains which colours you can buy buildings at.
	 */
	private String[] canBuildOnColourString(Player player) {
		String[] colours = { "Blå", "Orange", "Grøn", "Grå", "Rød", "Hvid", "Gul", "Lilla" };
		int trueCount = 0;
		//Counts how many different colours you can build buildings at.
		for (int i = 0; i < canBuildOnColour(player).length; i++) {
			if (canBuildOnColour(player)[i]) {
				trueCount++;
			}
		}
		//New String array containing only the colours that you can build buildings at.
		int j = 0;
		String[] canBuildOn = new String[trueCount];
		for (int i = 0; i < canBuildOnColour(player).length; i++) {
			
			if (canBuildOnColour(player)[i] == true) {
				canBuildOn[j] = colours[i];
				j++;
			}
		}
		return canBuildOn;
	}

	/**
	 * Method numbOfStreetsFromColour: Returns how many streets that is required 
	 * to be able to build a building depending on the colour.
	 * @param colour The colour of the street.
	 * @return 2 if the street is blue 3 otherwise.
	 */
	private int numbOfStreetsFromColour(String colour) {
		int colourAmount = 0;
		if (colour.equals("Blå") || colour.equals("Lilla")) {
			colourAmount = 2;
		} else {
			colourAmount = 3;
		}
		return colourAmount;
	}

	/**
	 * Method minimum: Returns the minimum of an array.
	 * @param numbs The array to find the minimum from.
	 * @return The smallest element of the array.
	 */
	private int minimum(int[] numbs) {
		//Set an abretary high value
		int min = 420;
		
		for(int i = 0; i < numbs.length; i++)
		{
			min = Math.min(min, numbs[i]);
		}
		return min;
	}

	/**
	 * Method findStreetNames: Returns all street names with the given colour that the player owns that he can build on.
	 * @param player The player that owns the streets.
	 * @param colour The colour that you want the streets return to have.
	 * @return street names as a String array with the given colour that the player owns.
	 */
	private String[] findStreetNames(Player player, String colour) {
		//An array to hold the amount of houses on the different streets.
		int[] houses = new int[numbOfStreetsFromColour(colour)];
		//An array to hold the names of the different streets.
		String[] streetNames = new String[numbOfStreetsFromColour(colour)];
		
		//Fills the arrays with informations.
		int j = 0;
		for (int i = 0; i < player.getFields().length; i++) {
			
			if (player.getFields()[i].getColour().equals(colour)) {
				streetNames[j] = player.getFields()[i].getName();
				houses[j] = ((Street) (player.getFields()[i])).getNumbOfHouses();
				j++;
			}
		}
		//Finds which street has the fewest houses.
		int minimum = minimum(houses);
		//Hvis der er hoteller på alle grunde:
		if (minimum==5){
			return null;
		}
		
		int amountOfStreets = 0;
		//Finds how many streets that has the minimum amount of houses
		for (int i = 0; i < streetNames.length; i++) {
			if (houses[i] == minimum) {
				amountOfStreets++;
			}
		}
		//An array to hold the name of the differents streets that you can build on.
		String[] streetNamesNew = new String[amountOfStreets];
		j=0;
		for (int i = 0; i < streetNames.length; i++) {
			if (minimum == houses[i]) {
				streetNamesNew[j] = streetNames[i];
				j++;
			}
		}
		return streetNamesNew;
	}

	/**
	 * Method setBuilding: Sets a house or a hotel on the street on the street owned by the player.
	 * @param player The player who wants to build a house/hotel.
	 * @param streetName The name of the street.
	 */
	private void setBuilding(Player player, String streetName)
	{
		Street street = player.getFieldFromName(streetName);
		int numbOfHouses = street.getNumbOfHouses();
		
		if (numbOfHouses == 4)
		{
			setHotel(player, street);
		}
		else if (numbOfHouses < 4 && numbOfHouses >= 0)
		{
			setHouse(player, street);
		}
		GUI.setBalance(player.getName(), player.getAccountBalance());
	}
	
	/**
	 * Method setHotel: Sets a hotel and removes houses on a street owned by the player and updates relevant information.
	 * @param player The player who wants to build a hotel.
	 * @param street The street the player wants to build on.
	 */
	
	private void setHotel(Player player, Street street)
	{
		if (getHotels() > 0)
		{
			if (player.getAccountBalance() > street.getHousePrice())
			{
				GUI.setHotel(street.getFieldNumber(), true);
				
				street.changeNumbOfHouses(1);
				player.changeAccountBalance(-street.getHousePrice());
				changeHouses(4);
				changeHotels(-1);
			}
			else
			{
				GUI.getUserButtonPressed("Du har ikke råd til at købe et hotel på denne grund.", "Ok");
			}
		}
		else
		{
			GUI.getUserButtonPressed("Der er ikke flere hoteller tilbage i banken.", "Ok");
		}
	}
	
	/**
	 * Method setHouse: Sets a house on a street owned by the player and updates relevant information.
	 * @param player The player who wants to build a house.
	 * @param street The street the player wants to build on.
	 */
	private void setHouse(Player player, Street street) {
		if (getHouses() > 0)
		{
		if (player.getAccountBalance() > street.getHousePrice()) {
			// Adds an amount of houses to the GUI and changes the value ofæ
			// numbOfHouses.
			GUI.setHouses(street.getFieldNumber(), street.changeNumbOfHouses(1));
			player.changeAccountBalance(-street.getHousePrice());
			changeHouses(-1);
		} else {
			GUI.getUserButtonPressed("Du har ikke råd til at købe et hus på denne grund.", "Ok");
		}
		}
		else
		{
			GUI.getUserButtonPressed("Der er ikke flere huse tilbage i banken.", "Ok");
		}
	}

	/**
	 * Method showHouseMenu: Shows on the GUI which options the player has <br>
	 * when he wants to buy a building on a street 
	 * @param player The player who wants to buy a building
	 */
	public void showBuidlingMenu(Player player) {
		while (true) {
			String[] options = MainController.addReturnToArray(canBuildOnColourString(player));
			String out="Hvilken farve ejendom vil du købe huse på?";
			if(options.length==1){
				out="Du har ikke nogle grunde at købe huse på.";
			}
			String answer = GUI.getUserSelection(out, options);
			if (answer.equals("Gå tilbage")) {
				return;
			} 
			else {
				while (true) 
				{	
					String[] options2 = MainController.addReturnToArray(findStreetNames(player, answer));
					String answer2;
					if(options2.length==1){
						answer2 = GUI.getUserSelection("Du kan ikke bygge flere bygninger på denne farve grunde.", options2);
					}
					else{
					answer2 = GUI.getUserSelection("Du har valgt " + answer + ". Bygningerne på " + answer + " koster: "
							+ player.getHousePriceFromColour(answer), options2) + " kr.";
					}
					if (answer2.equals("Gå tilbage")) {
						break;
					}
					setBuilding(player, answer2);
				}
			}
		}
	}
}
