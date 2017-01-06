package entity.ChanceCard;

	public class Payment extends ChanceCard{

		private String type;
		private String description;
		private int price;
		
		public Payment(String type, String description, int price)
		{
			super(type, description);
		}
}

