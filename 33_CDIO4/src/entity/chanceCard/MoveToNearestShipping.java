package entity.chanceCard;

public class MoveToNearestShipping extends Movement {
	
	//Instance variables
	private int[] shippingPositions;
	private boolean doubleRent;
	
	public MoveToNearestShipping(String type, String description, int[] shippingPositions, boolean doubleRent)
	{
		super(type, description);
		this.shippingPositions = shippingPositions;
		this.doubleRent = doubleRent;
	}

	/**
	 * Method getShippingPositions: Returns the position of the shipping fields.
	 * @return The position of the shipping fields.
	 */
	public int[] getShippingPositions()
	{
		return shippingPositions;
	}

	/**
	 * Method getDoubleRent: Returns if the player should pay double rent.
	 * @return True if the player should pay double rent. False otherwise.
	 */
	public boolean getDoubleRent()
	{
		return doubleRent;
	}
}
