package entity.chanceCard;

public class Grant extends ChanceCard{

	//Instance variables
	private int amount;

	public Grant(String type, String description,int amount)
	{
		super(type,description);
		this.amount = amount;
	}
	
	/**
	 * Method getAmount: Returns the amount of the Grant.
	 * @return The amount of the grant
	 */
	public int getAmount()
	{
		return amount;
	}
}