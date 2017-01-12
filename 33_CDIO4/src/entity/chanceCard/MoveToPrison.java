package entity.chanceCard;

/**
 * This class describes the specific chanceCard MoveToPrison.
 * @author Gruppe33
 *
 */
public class MoveToPrison extends Movement {
	
	//Instance variables
	private boolean sentToPrison;
	
	/**
	 * Constructor: Constructs a MoveToPrison ChanceCard.
	 * @param type The type of the Movement ChanceCard.
	 * @param description The description of Movement ChanceCard.
	 */
	public MoveToPrison(String type, String description)
	{
		super(type, description);
		this.sentToPrison = true;
	}

	/**
	 * Method getSentToPrison: Returns if the player should be sent to prison.
	 * @return True if the player should be sent to prison and false otherwise.
	 */
	public boolean getInPrison()
	{
		return sentToPrison;
	}
}
