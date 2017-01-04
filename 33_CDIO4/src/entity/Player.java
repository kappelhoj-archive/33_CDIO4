package entity;

import entity.field.*;

public class Player {

	//Instance variables
	private String playerName;       //The name of the player.
	private Account account;         //The account of the player.
	private boolean hasLost;         //Tells if the player has lost the game.
	private int propertyFortune;  	     //Sum of player's balance and the value of all the player's field.
	private boolean payDecision;     //True if player chooses to pay 10 % of his fortune, false otherwise.
	private int position;
	private Field[] fields;

	/** 
	 * Object Player constructor.
	 * @param The name of the player.
	 */
	public Player(String playerName)
	{
		this.playerName = playerName;
		account = new Account(30000);
		hasLost = false;
		propertyFortune = 0;
		position=0;
		fields = null;
	}
	
	/** 
	 * Method getPlayerName returns the name of the player.
	 * @return The name of the player.
	 */
	public String getPlayerName()
	{
		return playerName;
	}

	/**
	 * Method getPlayerFortune returns the fortune of the player.
	 * @return The fortune of the player.
	 */
	public int getPlayerFortune()
	{
		return propertyFortune + account.getBalance();
	}
		
	/**
	 * Method getPlayerPayDecision returns the pay decision of the player.
	 * @return The pay decision of the player.
	 */
	public boolean getPlayerPayDecision()
	{
		return payDecision;
	}
		
	/** 
	 * Method getAccountBalance returns the balance of the player's account.
	 * @return Returns the account balance value of the player.
	 */
	public int getAccountBalance()
	{
		return account.getBalance();
	}
	
	/**
	 * Method getPlayerHasLost returns true if the player has lost.
	 * @return True if the player hast lost the game.
	 */
	public boolean getPlayerHasLost()
	{
		return hasLost;
	}
	
	/**
	 * Method setPlayerDecision sets the pay decision of the player.
	 * @param playerPayDecision The pay decision of the player.
	 */
	public void setPayDecision(boolean playerPayDecision)
	{
		payDecision = playerPayDecision;
	}
	
	/**
	 * Method setPlayerHasLost sets if the player has lost.
	 * @param condition The condition to be set. If condition is true then player has lost.
	 */
	public void setPlayerHasLost(boolean condition) 
	{
		hasLost = condition;
	}
	
	/** 
	 * Method changeAccountBalance changes balance of the player's account with the parameter value.
	 * @param The value the balance should be changed with.
	 */
	public void changeAccountBalance(int value)
	{
		account.changeBalance(value);
	}
	
	/**
	 * Method changePlayerFortune changes the fortune of the player with the parameter value.
	 * @param value The value the fortune should be changed with.
	 */
	public void changePropertyFortune(int value)
	{
		propertyFortune = propertyFortune + value;
	}
	
	/**
	 * Method payRent The object pays the rent to the owner.
	 * @param owner The owner to be paid.
	 * @param rent The rent to be paid.
	 */
	public void payRent(Player owner, int rent)
	{
		//Checks if the player has enough money to pay the rent.
		if(account.getBalance() > rent)       
		{
			//Adds the rent to the balance of the owner.
			owner.changeAccountBalance(rent); 
			//Subtracts the rent from the objects balance.
			account.changeBalance(-rent);     
		}
		else
		{
			//Adds the object's balance to the balance of the owner.
			owner.changeAccountBalance(account.getBalance()); 
			//Sets the object's hasLost condition to true.
			setPlayerHasLost(true);                           
		}
	}

	/**
	 * Method getPosition returns the position of the player
	 * @return Returns the position of the player
	 */
	public int getPosition() 
	{
		return position;
	}

	/**
	 * Method setPosition sets the position of the player.
	 * @param position The position to be set.
	 */
	public void setPosition(int position) 
	{
		this.position = position;
	}

	/**
	 * Method getBottlersOwned: Returns the amount of bottler fields owned by the player.
	 * @return The amount of bottler fields owned.
	 */
	public int getBottlersOwned()
	{
		int amountOfBottlers = 0;
		for(int i = 0; i < fields.length; i++)
		{
			if ("Bottler".equals(fields[i].getType()))
			{
				amountOfBottlers++;
			}
		}
		return amountOfBottlers;
	}
	
	/**
	 * Method getFleetsOwned: Returns the amount of fleet fields owned by the player.
	 * @return The amount of fleet fields owned.
	 */
	public int getFleetsOwned()
	{
		int amountOfFleets = 0;
		for(int i = 0; i < fields.length; i++)
		{
			if ("Fleet".equals(fields[i].getType()))
			{
				amountOfFleets++;
			}
		}
		return amountOfFleets;
	}
}