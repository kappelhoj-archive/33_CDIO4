package entity.field;

public class Street extends Ownable {
	// Instance variables
	private String colour;
	private int baseRent;
	private int housePrice;
	private int[] houseRent;
	private int numbOfHouses;
	private int pledge;

	/**
	 * constructor: Constructs a Street.
	 * 
	 * @param name The name of the field.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 * @param colour The specific colour of the field on the board.
	 * @param baseRent The base rent of the field.
	 * @param houseRent The rent based on the number of houses on the field.
	 * @param numbOfHouses The current number of houses built on the field.
	 * @param pledge The value of a pledged field.
	 */

	public Street(int fieldNumber,String name, String type, String description, int price, String colour, int baseRent, int housePrice,
			int[] houseRent, int pledge) 
	{
		super(fieldNumber,name, type, description, price);
		this.colour = colour;
		this.baseRent = baseRent;
		this.housePrice = housePrice;
		this.houseRent = houseRent;
		this.numbOfHouses = 0;
		this.pledge = pledge;
	}

	/**
	 * Method getColour: Returns the colour of the field.
	 * @return The colour of the field.
	 */
	@Override
	public String getColour() {
		return colour;
	}

	/**
	 * Method getBaseRent: Returns the rent to be paid.
	 * @return The base rent of the field.
	 */
	public int getBaseRent() {
		return baseRent;
	}

	/**
	 * Method getHouseRent: Returns the rent to be paid by a player landing on a
	 * built field.
	 * @return The rent based on the number of houses on the field.
	 */
	public int getHouseRent(int numbOfHouses) {
		return houseRent[numbOfHouses];
	}

	/**
	 * Method getRent: Calculates and returns the rent to be paid, depending on
	 * the number of houses.
	 * @return The rent to be paid on the field.
	 */
	public int getRent() {
		int rent = 0;

		if (numbOfHouses == 0) {
			rent = baseRent;
			
			int streetsNeeded;
			switch (colour) {
			case "Blå":
			case "Lilla":
				streetsNeeded=2;
				break;
			default:
				streetsNeeded=3;
				break;
			}
			if(super.getOwner().getStreetsOwned(colour)==streetsNeeded){
				rent=rent*2;
			}

		} 
		else 
		{
			rent = houseRent[numbOfHouses-1];
		}

		return rent;
	}

	/**
	 * Method getPledge: Returns the value of the pledge given to the player, by
	 * the bank, when the player pledges the field.
	 */
	public int getPledge() {
		return pledge;
	}

	/**
	 * Method getValue: Returns the value of field (field price + prices for
	 * houses). <br>
	 * 
	 * @return The value of the field.
	 */
	@Override
	public int getValue() {
		return super.getValue() + numbOfHouses * housePrice;
	}

	/**
	 * Method getHousePrice: Returns the price of a house on the field.
	 * @return The price of a house on the field.
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Method getNumbOfHouses: Returns the number of houses on the street.
	 * @return The number of the houses on the street.
	 */
	public int getNumbOfHouses() {
		return numbOfHouses;
	}

	/**
	 * Method changeOfNumbOfHouses: Change the numbOfhouses variable with the given amount.
	 * @param amount The amount to change the variable with.
	 */
	public int changeNumbOfHouses(int amount)
	{
		numbOfHouses = numbOfHouses + amount;
		return numbOfHouses;
	}
}
