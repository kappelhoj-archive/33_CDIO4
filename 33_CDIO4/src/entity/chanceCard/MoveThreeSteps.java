package entity.chanceCard;

/**
 * This class describes the specific chanceCard MoveThreeSteps.
 * @author Gruppe33
 *
 */
public class MoveThreeSteps extends Movement {
	
	//Instance variables
	private int steps;
	
	/**
	 * Constructor: Constructs a MoveThreeSteps Movement ChanceCard.
	 * @param type The type of the Movement ChanceCard.
	 * @param description The description of the Movement ChanceCard.
	 * @param steps The amount of steps to move.
	 */
	public MoveThreeSteps(String type, String description, int steps)
	{
		super(type, description);
		this.steps = steps;
	}
	
	/**
	 * Method getSteps(): Returns how many steps to move.
	 * @return The steps to move.
	 */
	public int getSteps()
	{
		return steps;
	}
}
