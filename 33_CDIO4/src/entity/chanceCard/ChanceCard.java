package entity.chanceCard;

public abstract class ChanceCard {
	
	//Instance variables
	private String type;
	private String description;

	
	public ChanceCard(String type, String description)
	{
		this.type = type;
		this.description = description;

	}
	
	/**
	 * Method getType(): Returns the type of the chanceCard.
	 * @return The type of the chance card.
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Method getDescription: Returns the description of the chanceCard.
	 * @return The description of the chance card.
	 */
	public String getDescription()
	{
		return description;
	}
}

	

