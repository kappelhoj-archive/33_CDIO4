package entity.ChanceCard;

public class Grant extends ChanceCard{

	private int amount;

	public Grant(String type, String description,int amount){
		super(type,description);
		this.amount = amount;

	}
	public int getAmount()
	{
		return amount;
	}




}