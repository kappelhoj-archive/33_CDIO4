package entity.chanceCard;

/**
 * This class describes the specific chanceCard Party.
 * @author Gruppe33
 *
 */
public class Party extends ChanceCard{
	
	//Instance variables
	private int cost;
	
	/**
	 * Constructor: Constructs a Party ChanceCard.
	 * @param type The type of the ChanceCard
	 * @param description The description of the ChanceCard
	 * @param cost The cost of the party.
	 */
	public Party(String type, String description,int cost)
	{
		super(type,description);
		this.cost = cost;
	}
	
	/**
	 * Method getPrice: Returns the cost of the party.
	 * @return The cost of the party.
	 */
	public int getCost()
	{
		return cost;
	}
}
