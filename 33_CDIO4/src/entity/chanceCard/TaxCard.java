package entity.chanceCard;

public class TaxCard extends ChanceCard 
{
	//Instance variables
	private int[] taxPrices;
	
	/**
	 * Constructor: Constructs a TaxCard ChanceCard.
	 * @param type The type of the ChanceCard.
	 * @param description The description of the ChanceCard.
	 * @param taxPrices The tax prices for houses and hotels.
	 */
	public TaxCard(String type, String description, int[] taxPrices)
	{
		super(type,description);
		this.taxPrices = taxPrices;
	}
	
	public int[] getTaxPrices()
	{
		return taxPrices;
	}
}
