package entity.field;

/**
 * This class is the general description of a field.
 * @author Gruppe33
 *
 */
public abstract class Field {
	
	//Instance variables
	private int fieldNumber;
	private String name;
	private String type;
	private String description;
	
	/**
	 * Abstract constructor: Constructs a field.
	 * @param The type of the field.
	 * @param The description of the field.
	 */
	public Field(int fieldNumber, String name, String type, String description)
	{
		this.fieldNumber = fieldNumber;
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	/**
	 * Method getType: Returns the type of the field.
	 * @return The type of the field.
	 */
	public String getType()
	{
		return type;
	}
	
	/**
	 * Method getDescription: Returns the description of the field.
	 * @return The description of the field.
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Method getName: Returns the name of the field.
	 * @return The name of the field.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Method getFieldNumber: Returns the number of the field.
	 * @return The number of the field.
	 */
	public int getFieldNumber()
	{
		return fieldNumber;
	}
	
	/**
	 * The method getRent: Returns the rent to be paid by the player who lands on an Ownable field. <br>
	 * A method to be overridden by subclasses.
	 * @return The rent to be paid.
	 */
	public abstract int getRent();

}
