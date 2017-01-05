package entity.field;

import entity.Player;
public abstract class Ownable extends Field {
	
	//Instance variables
	private int price;
	private Player owner;
	
	/**
	 * Abstract constructor: Constructs an ownable field.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 */
	public Ownable(String name, String type, String description, int price)
	{
		super(name, type, description);
		this.price = price;
		this.owner = null;
	}
	
	/**
	 * Method getOwner: Returns the owner of the field.
	 * @return The player who owns the field.
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * Method getPrice: Returns the price of the field.
	 * @return The price of the field.
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * Method removeOwner: Removes the current owner of the field.
	 */
	public void removeOwner()
	{
		owner = null;
	}
	
	/**
	 * Method setOwner: Sets the owner of the field to player.
	 * @param player The player to be set to own the field.
	 */
	public void setOwner(Player player)
	{
		owner = player;
	}
	
	/**
	 * Method isFieldOwned: Checks if the field is owned by a player.
	 * @return True if the field is owned.
	 */
	public boolean isFieldOwned()
	{
		if (owner == null) //Checks if the field has a owner.
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Method isFieldOwnedByAnotherPlayer: Checks if the field is owned by another player.
	 * @param player The player who landed on the field.
	 * @return true if the field is owned by another player, otherwise it returns false.
	 */
	public boolean isFieldOwnedByAnotherPlayer(Player player)
	{
		if(isFieldOwned())
		{
			if (owner.getName().equals(player.getName()))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Method getRent: Returns the rent to be paid by the player who lands on the Ownable field. <br>
	 * A method to be overridden by subclasses.
	 * @return The rent to be paid.
	 */
	public abstract int getRent();
	
	/**
	 * Method getValue: Returns the value of field (The price of the field).
	 * @return The value of the field.
	 */
	public int getValue()
	{
		return getPrice();
	}
}
