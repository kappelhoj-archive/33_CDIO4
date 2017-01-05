package entity.ChanceCard;

public class Grant extends ChanceCard{

	private String type;
	private String description;
	private int price;
	
	public Grant(String type, String description, int price)
	{
		super(type, description);
	}
	
}
