package entity;

/**
 * This class is responsible for handling the players currency balance.
 * @author Gruppe33
 *
 */
public class Account {
	
	// Instance variables
	private int balance;

	/**
	 * Constructor: Constructs an Account.
	 * @param balance The start balance of the account..
	 */
	public Account(int balance) {
		this.balance = balance;
	}

	/**
	 * Method getBalance: Returns the balance of the account.
	 * @return balance The Balance of the account.
	 */
	public int getBalance() 
	{
		return balance;
	}

	/**
	 * Method changeBalance: Calculates the new balance based on the parameter
	 * value.
	 * @param value The value that the balance of the account should be changed
	 * with. If the parameter is positive the method adds value to
	 * the balance. If the parameter is negative the method
	 * subtracts value from balance.
	 * @return The method return true if the change of balance succeeded. False
	 * otherwise.
	 */
	public void changeBalance(int value) 
	{
		// Checks if the balance overflows
		if (balance + value < balance && value > 0) 
		{
		}
		// Checks if the balance underflows
		else if (balance + value > balance && value < 0) 
		{
		}
		// Changes balance
		else 
		{
			balance = balance + value;
		}

	}
	
	/**
	 * OBS: This method is only used for testing.
	 * Method setBalance sets the balance of the Account.
	 */
	public void setBalance(int value) {
		balance = value;
	}
}