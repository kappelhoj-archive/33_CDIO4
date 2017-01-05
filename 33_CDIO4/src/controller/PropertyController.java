package controller;

import entity.Player;

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

	public boolean houseBuyAvailable(Player player)
	{
		
	}
}
