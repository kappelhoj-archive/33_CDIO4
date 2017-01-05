package controller;

import desktop_resources.GUI;
import entity.Player;
import controller.MainController;

public class PropertyController {
	
	private int houses;
	private int hotels;
	public PropertyController(int houses, int hotel)
	{
		this.houses = 32;
		this.hotels = 12;
	}
	
	/**
	 * Method getHouses: Returns the number of houses available.
	 * @return Amount of houses available.
	 */
	public int getHouses()
	{
		return houses;
	}
	
	/**
	 * Method getHotels: Returns the number of hotels available.
	 * @return Amount of hotels available.
	 */
	public int getHotels()
	{
		return hotels;
	}
	
	/**
	 * Method changeHouses: Changes the amount of houses available.
	 * @param amount The amount to change the houses available with.
	 */
	public void changeHouses(int amount)
	{
		houses = houses + amount;
	}
	
	/**
	 * Method changeHotels: Changes the amount of hotels available.
	 * @param amount The amount to change the hotels available with.
	 */
	public void changeHotels(int amount)
	{
		hotels = hotels + amount;
	}

	/**
	 * Method countStreetColours: Returns an array of integers which represents how many of each street colour the player owns.
	 * @param player The player who owns the fields.
	 * @return int array containing the information.
	 */
	private int[] countStreetColours(Player player)
	{
		int[] colours = new int[8];
		for(int i = 0; i < player.getfields().length;i++)
		{
			String colour = player.getfields()[i].getColour();
			switch(colour)
			{
			case "Blå": colours[0]++;
			break;
			case "Orange": colours[1]++;
			break;
			case "Grøn": colours[2]++;
			break;
			case "Grå": colours[3]++;
			break;
			case "Rød": colours[4]++;
			break;
			case "Hvid": colours[5]++;
			break;
			case "Gul": colours[6]++;
			break;
			case "Lilla": colours[7]++;
			break;
			}
		}
		return colours;
	}
	
	/**
	 * Method buyHouse: Returns which Streets the player can buy houses at.
	 * @param player The player who owns the streets.
	 * @return The boolean array which contains which streets you can buy houses at.
	 */
	public boolean[] buyHouseAvailable(Player player)
	{
		int[] colours = countStreetColours(player);
		//Streets colours
		int[] required = {2,3,3,3,3,3,3,2};
		boolean[] canBuy = new boolean[8];
		
		for (int i = 0; i < canBuy.length;i++)
		{
			canBuy[i] = colours[i] == required[i];
		}
		return canBuy;
	
	}
	
	public String[] buyableColours(Player player)
	{
		boolean[] bool = buyHouseAvailable(player);
		String[] colours = {"Blå", "Orange", "Grøn", "Grå", "rød", "Hvid", "Gul","lilla"}; 
		int trueCount = 0;
		for(int i = 0; i < bool.length; i++)
		{
			if(bool[i])
			{
				trueCount++;
			}
		}
		String[] buyAble = new String[trueCount];
		for(int i = 0; i < bool.length; i++)
		{
			int j = 0;
			if(bool[i] == true)
			{
				buyAble[j] = colours[j];
			}
		}
		return buyAble;
	}
	
	public void showHouseMenu(Player player)
	{
		String [] options = MainController.addReturnToArray(buyableColours(player));
		GUI.getUserSelection("Hvilken farve ejendom vil du købe huse på?", options);
	}
}
