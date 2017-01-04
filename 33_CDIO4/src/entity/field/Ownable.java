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
	public Ownable(String type, String description, int price)
	{
		super(type, description);
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
	 * The method getRent: Returns the rent to be paid by the player who lands on the Ownable field. <br>
	 * A method to be overridden by subclasses.
	 * @return The rent to be paid.
	 */
	public abstract int getRent();
}
