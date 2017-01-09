package entity.chanceCard;

public class MoveToField extends Movement {
	
	//Instance variables
	private int fieldPosition;
	
	public MoveToField(String type, String description, int fieldPosition)
	{
		super(type, description);
		this.fieldPosition = fieldPosition;
	}
	
	/**
	 * Method getMoveTo(): Returns the field position to move to..
	 * @return The field position to move to.
	 */
	public int getMoveTo()
	{
		return fieldPosition;
	}

}
