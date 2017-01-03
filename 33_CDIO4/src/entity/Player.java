package entity;
import entity.field.*;

public class Player {

	//Instance variables
	private String name;		//The name of the player.
	private Account account;	//The account of the player.
	private boolean lost;		//Tells if the player has lost the game.
	private boolean prison;		//Tells if the player is in prison.
	private int dicePair;		//The amount of times a player rolls a dice pair in a row.
	private int position;		//The board position of the player.
	private Field[] streets;	//The streets owned by the player

	/** 
	 * Object Player constructor.
	 * @param The name of the player.
	 */
	public Player(String name)
	{
		this.name = name;
		account = new Account(30000);
		lost = false;
		prison = false;
		dicePair = 0;
		position = 0;
		streets = null;		
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
			owner.setAccountBalance(rent); 
			//Subtracts the rent from the objects balance.
			account.changeBalance(-rent);     
		}
		else
		{
			//Adds the object's balance to the balance of the owner.
			owner.setAccountBalance(account.getBalance()); 
			//Sets the object's hasLost condition to true.
			setLost(true);                           
		}
	}
	
	/** 
	 * Method getPlayerName returns the name of the player.
	 * @return The name of the player.
	 */
	public String getName()
	{
		return name;
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
	 * Method changeAccountBalance changes balance of the player's account with the parameter value.
	 * @param The value the balance should be changed with.
	 */
	public void setAccountBalance(int value)
	{
		account.changeBalance(value);
	}
	
	/**
	 * Method getLost returns the player's lost status.
	 * @return The player's lost status. If true then the player has lost the game.
	 */
	public boolean getLost()
	{
		return lost;
	}
	
	/**
	 * Method setLost sets the player's lost status.
	 * @param condition The condition to be set. If condition is true then the player has lost.
	 */
	public void setLost(boolean condition) 
	{
		lost = condition;
	}
	
	/**
	 * Method getPrison returns the player's prison status.
	 * @return The player's prison status. If true then the player is in prison.
	 */
	public boolean getPrison()
	{
		return prison;
	}
	
	/**
	 * Method setPrison sets the player's prison status.
	 * @param condition The condition to be set. If condition is true then the player is in prison.
	 */
	public void setPrison(boolean condition)
	{
		prison = condition;
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
	 * Method getStreets returns the streets owned by the player.
	 * @return Returns the streets owned by the player.
	 */
	public Field[] getStreets()
	{
		return streets;
	}
	
	/**
	 * Method setStreets sets the streets owned by the player.
	 * @param street The street to be added to the player's street list.
	 */
	public void setStreets(Field street)
	{
		//Creates a new streets array with 1 more space than the original.
		Field[] streets = new Field[this.streets.length + 1];
		
		//Go through the original streets array and add its values to the new streets array.
		for(int i = 0; i < this.streets.length; i++)
		{
			streets[i] = this.streets[i];
		}
		
		//Add the newly bought street to the new streets array.
		streets[this.streets.length] = street;
		
		//Sets the original streets array to the new streets array.
		this.streets = streets;		
	}
	
	public int getDicePair()
	{
		return dicePair;
	}
	
	public void setDicePair(int dicePair)
	{
		this.dicePair = dicePair;
	}
}