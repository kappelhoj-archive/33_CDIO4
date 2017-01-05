package entity;
import entity.field.*;

public class Player {

	//Instance variables
	private String name;		//The name of the player.
	private Account account;	//The account of the player.
	private boolean lost;		//Tells if the player has lost the game.
	private boolean prison;		//Tells if the player is in prison.
	private int position;		//The board position of the player.
	private Ownable[] fields;	//The fields owned by the player

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
		position = 0;
		fields = null;		
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
	public void changeAccountBalance(int value)
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
	 * Method getfields returns the fields owned by the player.
	 * @return Returns the fields owned by the player.
	 */
	public Ownable[] getfields()
	{
		return fields;
	}

	/**
	 * Method setfields sets the fields owned by the player.
	 * @param street The street to be added to the player's street list.
	 */
	public void setfields(Field field)
	{
		Ownable ownable = (Ownable)(field);
		//Creates a new fields array with 1 more space than the original.
		Ownable[] newFields = new Ownable[fields.length + 1];

		//Go through the original fields array and add its values to the new fields array.
		for(int i = 0; i < fields.length; i++)
		{
			newFields[i] = fields[i];
		}

		//Add the newly bought street to the new fields array.
		newFields[fields.length] = ownable;

		//Sets the original fields array to the new fields array.
		fields = newFields;		
	}
	/**
	 * Method to remove fields from a players property list.
	 * @param field The specific field to be removed.
	 * @param player The player affected by this removal.
	 */
	public void loseFields (Ownable field, Player player)
	{
		if (field.getOwner().equals(player.getLost()))
		field.setOwner(null);
		if (field.getType().equals("Ejendom"))
		{
			((Street) field).setNumbOfHouses(0);
		}
		String removedField = field.getName();

		Ownable ownable = (Ownable)(field);
		Ownable[] fewerFields = new Ownable[fields.length - 1];

		for (int j = 0; j < fields.length; j++)
		{
			if (removedField.equals(fields[j]))
			{
				fewerFields[j] = fields[j+1];
			}
			
			else
			{
				fewerFields[j] = fields[j];
			}
		}
	}

	/**
	 * Method getBottlersOwned: Returns the amount of bottler fields owned by the player.
	 * @return The amount of bottler fields owned.
	 */
	public int getBeweriesOwned()
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
	public int getShippingsOwned()
	{
		int amountOfShippings = 0;
		for(int i = 0; i < fields.length; i++)
		{
			if ("Fleet".equals(fields[i].getType()))
			{
				amountOfShippings++;
			}
		}
		return amountOfShippings;
	}

	/**
	 * Method getPropertiesOwned: Returns the amount of properties owned by the player with the same colour.
	 * @param colour The colour of the field.
	 * @return The amount of properties with the given colour.
	 */
	public int getPropertiesOwned(String colour)
	{
		int numSameColour = 0;
		for(int i = 0; i < fields.length; i++)
		{
			Street field_i = (Street)(fields[i]);
			if ("Property".equals(fields[i].getType()) && colour.equals(field_i.getColour()))
			{
				numSameColour++;
			}
		}
		return numSameColour;
	}

	/**
	 * Method buyField Lets the player buy a Ownable field.
	 * @param player The player to buy the field.
	 * @return Returns true if the buy succeeded.
	 */
	public boolean buyField(Field field)
	{
		Ownable ownable = (Ownable)(field);
		if (getAccountBalance() > ownable.getPrice())  //Checks if the player has enough money to buy the field.
		{
			changeAccountBalance(-ownable.getPrice()); //Subtracts the price of the field from the player account balance.
			ownable.setOwner(this);                    //Sets the player to be the owner of the field.
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Method getFortune: Calculates and returns the total fortune of the player.
	 * @return The fortune of the player.
	 */
	public int getFortune()
	{
		int fortune = 0;
		for (int i = 0; 0 < fields.length; i++)
		{
			fortune = fortune + fields[i].getValue();
		}
		return fortune;
	}
}