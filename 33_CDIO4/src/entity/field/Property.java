package entity.field;

public class Property extends Ownable
{
	//Instance variables
	private int baseRent;
	private int houseRent[];
	private int numbOfHouses;
	private String colour;


	/**
	 * Object Property constructor
	 * @param type The type of the field.
	 * @param description The description of the field.
	 * @param price The price of the field.
	 * @param colour The specific colour of the field on the board.
	 * @param baseRent The base rent of the unbuilt field.
	 * @param houseRent The base rent of the empty field.
	 * @param numbOfHouses The current number of houses built on the field.
	 */

	public Property (String type, String description, int price, String colour, int baseRent, int houseRent[])
	{
		super(type, description, price);
		this.colour = colour;
		this.baseRent = baseRent;
		this.houseRent[0] = houseRent[0];
		this.houseRent[1] = houseRent[1];
		this.houseRent[2] = houseRent[2];
		this.houseRent[3] = houseRent[3];
		this.houseRent[4] = houseRent[4];
		this.numbOfHouses = 0;
	}

	/**
	 * The method getColour: Returns the colour of the field.
	 * @return The colour of the field.
	 */
	public String getColour(){
		return colour;
	}

	/**
	 * The method getBaseRent returns the rent to be payed 
	 */
	public int getBaseRent(){
		return baseRent;
	}

	/**
	 * The method getHouseRent returns the rent to be payed by a player landing on a built field.
	 */
	public int getHouseRent(int numbOfHouses){
		return houseRent[numbOfHouses];
	}
	/**
	 * The method getRent calculates and returns the rent to be payed, depending on the number of houses.
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
}
