package entity.chanceCard;

public class Prison extends ChanceCard{

	//Instance variables.
	private boolean prisonStatus;
	
	/**
	 * Constructor: Constructs a Prison ChanceCard.
	 * @param type The type of the ChanceCard.
	 * @param description The description of the ChanceCard.
	 */
	public Prison(String type, String description)
	{
		super(type,description);
		this.prisonStatus = false;
	}
}
