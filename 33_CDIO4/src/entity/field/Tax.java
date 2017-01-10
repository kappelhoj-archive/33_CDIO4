package entity.field;

public class Tax extends Field {
	
	//Instance variables
	private boolean hasTaxRate;
	private int amount;
	
	/**
	 * Constructor: Constructs Tax field.
	 * @param type The type of the field.
	 * @param amount The fixed tax amount the player can choose to pay.
	 * @param hasTaxRate If hasTaxRate is true the player can choose to pay 10% of his fortune.
	 */
	public Tax (int fieldNumber, String name, String type, String description, boolean hasTaxRate, int amount)
	{	
		super(fieldNumber, name, type, description);
		this.amount = amount;
		this.hasTaxRate = hasTaxRate;
	}
	
	/**
	 * Method getAmount: Returns the amount to be paid by the player who lands on the tax field.
	 * @return Returns the tax amount to be paid.
	 */
	public int getAmount()
	{
		return amount;
	}
	
	/**
	 * Method getRate: Returns if the player can choose to pay 10% taxRate.
	 * @return Returns True if the player can choose to pay 10% and false otherwise.
	 */
	public boolean getHasTaxRate()
	{
		return hasTaxRate;
	}
	
	/**
	 * Method getRent: This method is not used but makes other classes simpler.
	 */
	@Override
	public int getRent(){
		return -1;
	}
}
