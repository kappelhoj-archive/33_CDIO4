package entity.chanceCard;

/**
 * This class describes a simple chance card.
 * @author Gruppe33
 *
 */
public abstract class ChanceCard {
	
	//Instance variables
	private String type;
	private String description;

	/**
	 * Constructor: Constructs a ChanceCard
	 * @param type The type of the ChanceCard
	 * @param description The description of the ChanceCard
	 */
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

	

