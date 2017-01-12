package entity;

import entity.Die;

/**
 * This class is responsible for rolling 2 object of type Die, and returning that information to the main controller.
 * @author Gruppe33
 *
 */
public class DiceCup 
{	
	//Instance variables
	private Die d1;
	private Die d2;
	
	/**
	 * Constructor: Constructs a DiceCup.
	 * The diceCup object contains two die Objects.
	 */
	public DiceCup()
	{
		d1 = new Die();
		d2 = new Die();
	}
	
	/**
	 * Method rollDice: Rolls the dice in the DiceCup.
	 */
	public void shake()
	{
		d1.rollDie();
		d2.rollDie();
	}

	/**
	 * Method getDiceValue: returns the value of the rolled dice as an integer array.
	 * @return Returns the array with the DiceValues.
	 */
	public int[] getDiceValue()
	{
		int[] array = {d1.getValue(), d2.getValue()};
		return array;
	}	
}
