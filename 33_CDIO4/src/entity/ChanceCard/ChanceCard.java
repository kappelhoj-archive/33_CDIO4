package entity.ChanceCard;

public abstract class ChanceCard {
	
	private String type;
	private String desc;

	
	public ChanceCard(String type,String desc)
	{
		this.type = type;
		this.desc = desc;

	}
	public String getType()
	{
		return type;
	}
	public String getDesc()
	{
		return desc;
	}

	
}

	

