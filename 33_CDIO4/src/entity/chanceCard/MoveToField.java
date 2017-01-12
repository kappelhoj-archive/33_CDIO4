package entity.chanceCard;


/**
 * This class describes the specific chanceCard MoveToField.
 * @author Gruppe33
 *
 */
public class MoveToField extends Movement {
	
	//Instance variables
	private int fieldPosition;
	
	/**
	 * Constructor: Constructs a MoveToField Movement ChanceCard.
	 * @param type The type of the Movement ChanceCard.
	 * @param description The description of the Movement ChanceCard.
	 * @param fieldPosition The position of the field to move to.
	 */
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
