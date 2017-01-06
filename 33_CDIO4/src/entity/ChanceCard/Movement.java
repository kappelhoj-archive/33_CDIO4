package entity.ChanceCard;

public class Movement extends ChanceCard{

	private int move;
	private int fieldPosition[];
	private boolean sentToPrison;
	private boolean doubleRent;
	
	public Movement(String type, String description, int move, int fieldPosition[], boolean sentToPrison, boolean doubleRent){
		super(type,description);
		this.move = move;
		this.fieldPosition = fieldPosition;
		this.sentToPrison = sentToPrison;
		this.doubleRent = doubleRent;
	}
	/**
	 * Method getMove(): Returns the amount to move.
	 * @return The amount to move.
	 */
	public int getMove()
	{
		return move;
	}
	
	/**
	 * Method getFields: Returns the position of the fields.
	 * @return The position of the fields.
	 */
	public int[] getFieldPosition()
	{
		return fieldPosition;
	}

	/**
	 * Method getSentToPrison: Returns if the player should be sent to prison.
	 * @return True if the player should be sent to prison and false otherwise.
	 */
	public boolean getSentToPrison()
	{
		return sentToPrison;
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
