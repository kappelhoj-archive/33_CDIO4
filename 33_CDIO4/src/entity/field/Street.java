package entity.field;

public class Street extends Ownable
{
	//Instance variables
	private String colour;
	private int baseRent;
	private int housePrice;
	private int houseRent[];
	private int numbOfHouses;
	private int pledge;


	/**
	 * constructor: Constructs a Street.
	 * @param name The name of the field.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 * @param colour The specific colour of the field on the board.
	 * @param baseRent The base rent of the unbuilt field.
	 * @param houseRent The base rent of the empty field.
	 * @param numbOfHouses The current number of houses built on the field.
	 * @param pledge The value of a pledged field.
	 */

	public Street (String name, String type, String description, int price, String colour, int baseRent, int housePrice, int houseRent[], int pledge)
	{
		super(name, type, description, price);
		this.colour = colour;
		this.baseRent = baseRent;
		this.housePrice = housePrice;
		this.houseRent[0] = houseRent[0];
		this.houseRent[1] = houseRent[1];
		this.houseRent[2] = houseRent[2];
		this.houseRent[3] = houseRent[3];
		this.houseRent[4] = houseRent[4];
		this.numbOfHouses = 0;
		this.pledge = pledge;
	}

	/**
	 * Method getColour: Returns the colour of the field.
	 * @return The colour of the field.
	 */
	public String getColour(){
		return colour;
	}

	/**
	 * Method getBaseRent: Returns the rent to be paid.
	 */
	public int getBaseRent(){
		return baseRent;
	}

	/**
	 * Method getHouseRent: Returns the rent to be paid by a player landing on a built field.
	 */
	public int getHouseRent(int numbOfHouses){
		return houseRent[numbOfHouses];
	}
	/**
	 * Method getRent: Calculates and returns the rent to be paid, depending on the number of houses.
	 */
	public int getRent(){
		int rent = 0;

		if (numbOfHouses == 0)
		{
			rent = baseRent;
		}
		else
		{
			rent = houseRent[numbOfHouses];
		}

		return rent;
	}

	/**
	 * Method getPledge: Returns the value of the pledge given to the player, by the bank, when the player pledges the field.
	 */
	public int getPledge(){
		return pledge;
	}

	/**
	 * Method getValue: Returns the value of field (field price + prices for houses). <br>
	 * @return The value of the field.
	 */
	@Override
	public int getValue()
	{
		return super.getValue() + numbOfHouses * housePrice;
	}

	public int getHousePrice()
	{
		return housePrice;
	}


	/**
	 * Methods needed for the Bank class. Might need revisiting later.
	 */
	public int getNumbOfHouses()
	{
		return numbOfHouses;
	}
	public int subtractNumbOfHouses()
	{
		if (numbOfHouses == 0)
		{
			System.out.println("Du har ikke nogen huse på " + getName() + ".");
		}
		if (numbOfHouses > 0)
		{
			numbOfHouses--;
			System.out.println("Ét hus er blevet fjernet fra " + getName() + ".");
		}
		return numbOfHouses;
	}
	
	public void setNumbOfHouses(int houseLoss)
	{
		numbOfHouses = houseLoss;
	}
}
