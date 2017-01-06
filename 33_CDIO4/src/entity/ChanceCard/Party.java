package entity.ChanceCard;

public class Party extends ChanceCard{

	private String type;
	private String description;
	
	public Party(String type, String description,int price){
		super(type,description);
	}
}
