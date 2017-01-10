package entity.chanceCard;

public class MoveToNearestShipping extends Movement {
	
	//Instance variables
	private int[] shippingPositions;
	private boolean doubleRent;
	
	/**
	 * Constructor: Constructs a MoveToNearestShipping Movement ChanceCard.
	 * @param type The type of the Movement ChanceCard.
	 * @param description The description of the Movement ChanceCard.
	 * @param shippingPositions The positions of all the shipping fields.
	 * @param doubleRent True if you should pay double rent.
	 */
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
