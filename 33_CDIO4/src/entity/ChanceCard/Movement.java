package entity.ChanceCard;

public class Movement extends ChanceCard{

	private String type;
	private String description;
	private int move;
	private int field1;
	private int field2;
	private int field3;
	private int field4;
	private boolean inprisoned;
	private boolean doublerent;
	
	public Movement(String type, String description, int move, int field1, int field2, int field3, int field4, boolean inprisoned,boolean doublerent){
		super(type,description);
	}
	public int getMove()
	{
		return move;
	}
	public int getField1()
	{
		return field1;
	}
	public int getField2()
	{
		return field2;
	}
	public int getField3()
	{
		return field3;
	}
	public int getField4()
	{
		return field4;
	}
	public boolean getInprisoned()
	{
		return inprisoned;
	}
	public boolean getDoubleRent()
	{
		return doublerent;
	}
	
}
