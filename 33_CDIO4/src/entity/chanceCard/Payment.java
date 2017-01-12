package entity.chanceCard;

/**
 * This class describes the specific chanceCard Payment.
 * @author Gruppe33
 *
 */
	public class Payment extends ChanceCard{

		
		//Instance variables
		private int amount;
		
		/**
		 * Constructor: Constructs a Payment ChanceCard.
		 * @param type The type of the ChanceCard.
		 * @param description The description of the ChanceCard.
		 * @param amount The amount of the payment.
		 */
		public Payment(String type, String description, int amount)
		{
			super(type, description);
			this.amount = amount;
		}
		
		/**
		 * Method getAmount: Returns the amount of the payment.
		 * @return The amount of the payment.
		 */
		public int getAmount()
		{
			return amount;
		}
		
}

