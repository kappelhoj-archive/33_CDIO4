package entity.chanceCard;

public class TaxCard extends ChanceCard{

	private String type;
	private String description;
	
	public TaxCard(String type, String description,int tax){
		super(type,description);
	}
}
