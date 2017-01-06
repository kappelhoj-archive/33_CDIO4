package entity.chanceCard;

public class Party extends ChanceCard{
	
	//Instance variables
	private int cost;
	
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
