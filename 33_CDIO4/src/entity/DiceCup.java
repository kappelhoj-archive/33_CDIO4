package entity;

import entity.Die;

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
	public void shakeCup()
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
