package entity.ChanceCard;

public class Grant extends ChanceCard{

	private String type;
	private String desc;
	private int price;
	
	public Grant(String type, String desc, int price)
	{
		super(type, desc);
	}
}
