package entity.chanceCard;

public class MoveThreeSteps extends Movement {
	
	//Instance variables
	private int steps;
	
	public MoveThreeSteps(String type, String description, int steps)
	{
		super(type, description);
		this.steps = steps;
	}
	
	/**
	 * Method getMove(): Returns how many steps to move.
	 * @return The steps to move.
	 */
	public int getMoveTo()
	{
		return steps;
	}
}
