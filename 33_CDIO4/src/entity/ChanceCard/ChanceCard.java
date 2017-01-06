package entity.ChanceCard;

public abstract class ChanceCard {
	
	private String type;
	private String description;

	
	public ChanceCard(String type,String description)
	{
		this.type = type;
		this.description = description;

	}
	public String getType()
	{
		return type;
	}
	public String getDesc()
	{
		return description;
	}

	
}

	

