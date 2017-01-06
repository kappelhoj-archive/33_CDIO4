package entity.ChanceCard;

public class Grant extends ChanceCard{

	private String type;
	private String description;
	private int amount;

	public Grant(String type, String description,int amount){
		super(type,description);

	}
	public int getAmount()
	{
		return amount;
	}




}