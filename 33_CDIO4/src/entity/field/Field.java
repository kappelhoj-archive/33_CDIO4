package entity.field;

public abstract class Field {
	
	//Instance variables
	private String type;
	private String description;
	
	/**
	 * Abstract constructor: Constructs a field.
	 * @param The type of the field.
	 * @param The description of the field.
	 */
	public Field(String type, String description)
	{
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
	 * The method getRent: Returns the rent to be paid by the player who lands on an Ownable field. <br>
	 * A method to be overridden by subclasses.
	 * @return The rent to be paid.
	 */
	public abstract int getRent();

}
