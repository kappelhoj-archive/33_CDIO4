package entity.ChanceCard;

public class Prison extends ChanceCard{

	private String type;
	private String description;
	
	public Prison(String type, String description,boolean startGrant,boolean inprisoned){
		super(type,description);
	}
}
