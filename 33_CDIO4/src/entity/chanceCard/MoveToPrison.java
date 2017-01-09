package entity.chanceCard;

public class MoveToPrison extends Movement {
	
	//Instance variables
	private boolean sentToPrison;
	
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
