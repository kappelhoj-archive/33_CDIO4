package entity.field;

public class Tax extends Field {
	
	//Instance variables
	private boolean taxRateAvailable;
	private int amount;
	
	/**
	 * Tax constructor
	 * @param type The type of the field
	 * @param amount The fixed tax amount the player can choose to pay.
	 * @param rate The tax rate the player can choose to pay. The rate is in percent
	 */
	public Tax (String name, String type, String description, boolean taxRateAvailable, int amount)
	{	
		super(name, type, description);
		this.amount = amount;
		this.taxRateAvailable = taxRateAvailable;
	}
	
	/**
	 * Method getAmount returns the amount to be paid by the player who lands on the tax field.
	 * @return Returns the tax amount to be paid.
	 */
	public int getAmount()
	{
		return amount;
	}
	
	/**
	 * Method getRate returns tax rate to be paid by the player who lands on the tax field.
	 * @return Returns the tax rate to be paid.
	 */
	public boolean getTaxRateAvailable()
	{
		return taxRateAvailable;
	}
	
	
	/**
	 * Method getRent is not used.
	 * The getRent has to be implemented because the super class has the abstract method getRent().
	 */
	@Override
	public int getRent(){
		return -1;
	}
}
