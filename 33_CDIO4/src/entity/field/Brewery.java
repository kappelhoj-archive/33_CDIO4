package entity.field;

public class Brewery extends Ownable{

	//Instance variables
	private int baseRent;
	private int diceSum;
	
	/**
	 * Constructor: Constructs a brewery.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 */
	public Brewery(int fieldNumber,String name, String type, String description, int price)
	{
		super(fieldNumber, name, type, description, price);
		this.baseRent = 100;
	}
	
	/**
	 * Method setDiceSum: Sets the value of diceSum.
	 * @param diceSum The diceSum to be set.
	 */
	public void setDiceSum(int diceSum)
	{
		this.diceSum = diceSum;
	}
	
	/**
	 * Method getRent: Returns the rent to be paid by the player who lands a brewery field.
	 * @return The rent to be paid.
	 */
	@Override
	public int getRent()
	{
		int numbOfBreweries = super.getOwner().getBreweriesOwned();
		int rent = baseRent * diceSum * numbOfBreweries;
		return rent;
	}
}
