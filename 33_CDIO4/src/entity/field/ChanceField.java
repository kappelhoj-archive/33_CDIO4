package entity.field;

public class ChanceField extends Field{
	
	public ChanceField(int fieldNumber, String name, String type, String description)
	{
		super(fieldNumber, name, type, description);
	}
	
	public int getRent()
	{
		return -1;
	}
}
