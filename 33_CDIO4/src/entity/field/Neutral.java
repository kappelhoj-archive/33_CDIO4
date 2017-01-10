package entity.field;

public class Neutral extends Field
{

	/**
	 * Constructor: Constructs a Neutral field.
	 * @param fieldNumber The number of the field.
	 * @param name The name of the field.
	 * @param type The type of the field.
	 * @param description The description of the field.
	 */
	public Neutral (int fieldNumber, String name, String type, String description){
		super(fieldNumber, name, type, description);
	}

	/**
	 * Method getRent: This method is not used but makes other classes simpler.
	 */
	@Override
	public int getRent() 
	{
	return -1;
	}
}
