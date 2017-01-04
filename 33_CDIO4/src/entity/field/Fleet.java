package entity.field;

public class Fleet extends Ownable {

	//Constants
	final private int[] RENT = {500, 1000, 2000, 4000};
	
	/**
	 * Constructor: Constructs a fleet.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 */
	public Fleet(String type, String description, int price)
	{
		super(type, description, price);
	}
	
	/**
	 * Method getRent: Returns the rent to be paid by the player who lands on a fleet field.
	 * @return The rent to be paid.
	 */
	@Override
	public int getRent()
	{
		int amountOfFleets = super.getOwner().getFleetsOwned(); //The amount of fleet fields the owner of the fleet field owns.
		int rent;
		switch(amountOfFleets)
		{
		case 1: rent = RENT[0]; //The rent to be paid if the owner owns one fleet field.
			break;
		case 2: rent = RENT[1]; //The rent to be paid if the owner owns two fleet fields.
			break;
		case 3: rent = RENT[2]; //The rent to be paid if the owner owns three fleet fields.
			break;
		case 4: rent = RENT[3]; //The rent to be paid if the owner owns all fleet fields.
			break;
		default: rent = 0;
		}
		return rent;
	}
}
