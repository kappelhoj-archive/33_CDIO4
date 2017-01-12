package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import entity.chanceCard.*;

/**
 * This class is responsible for creating, holding and giving class cards. This
 * is primarily used by the chancecard controller.
 * 
 * @author Gruppe33
 *
 */
public class ChanceCardDeck {
	// Instance variables
	private ChanceCard[] chanceCardDeck;

	/**
	 * Constructor: Constructs a chanceCard deck.
	 */
	public ChanceCardDeck() {
		chanceCardDeck = new ChanceCard[37];
		int cardNumber = 0;

		// Imports file
		String fileName = "data.csv";
		File file = new File(fileName);

		// Scans the file and splits it into an array based on the position of ,
		try {
			Scanner inputStream = new Scanner(file);
			String data = inputStream.nextLine();
			for (int i = 0; i < chanceCardDeck.length; i++) {
				data = inputStream.nextLine();
				String[] fromInfo = data.split(",");

				switch (fromInfo[0]) {
				case "Grant":
					chanceCardDeck[cardNumber] = createGrant(fromInfo);
					break;
				case "Party":
					chanceCardDeck[cardNumber] = createParty(fromInfo);
					break;
				case "Payment":
					chanceCardDeck[cardNumber] = createPayment(fromInfo);
					break;
				case "Prison":
					chanceCardDeck[cardNumber] = createPrison(fromInfo);
					break;
				case "TaxCard":
					chanceCardDeck[cardNumber] = createTaxCard(fromInfo);
					break;
				case "MoveToNearestShipping":
					chanceCardDeck[cardNumber] = createMoveToNearestShipping(fromInfo);
					break;
				case "MoveToPrison":
					chanceCardDeck[cardNumber] = createMoveToPrison(fromInfo);
					break;
				case "MoveToField":
					chanceCardDeck[cardNumber] = createMoveToField(fromInfo);
					break;
				case "MoveThreeSteps":
					chanceCardDeck[cardNumber] = createMoveThreeSteps(fromInfo);
					break;
				default:
					System.out.println("Error: This card is unknown");
					System.out.println(chanceCardDeck[cardNumber].getType());
					break;
				}

				cardNumber++;

			}
			shuffle();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method draw: Returns the first card in the deck and afterwards puts it in
	 * the bottom of the deck
	 * 
	 * @return The first card of the deck.
	 */
	public ChanceCard draw() {
		ChanceCard first = chanceCardDeck[0];

		for (int i = 0; i < chanceCardDeck.length - 1; i++) {
			chanceCardDeck[i] = chanceCardDeck[i + 1];
		}

		chanceCardDeck[chanceCardDeck.length - 1] = first;
		return first;
	}

	/**
	 * Method shuffle: Shuffles the chance card deck.
	 */
	public void shuffle() {
		for (int i = 0; i < 10000; i++) {
			swapTwoCards();
		}
	}

	/**
	 * Method swapTwoCards: Swaps two chance cards in the deck.
	 */
	private void swapTwoCards() {
		Random generator = new Random();
		int cardNumber1 = generator.nextInt(chanceCardDeck.length);
		ChanceCard temp = chanceCardDeck[cardNumber1];
		int cardNumber2 = generator.nextInt(chanceCardDeck.length);
		chanceCardDeck[cardNumber1] = chanceCardDeck[cardNumber2];
		chanceCardDeck[cardNumber2] = temp;
	}

	/**
	 * Method creatGrant: Returns a Grant chanceCard from the given information.
	 * 
	 * @param i
	 *            The information given.
	 * @return The Grant chanceCard.
	 */
	private Grant createGrant(String[] i) {
		Grant grant = new Grant(i[0], i[1], toInt(i[2]));
		return grant;
	}

	/**
	 * Method createTaxCard: Returns a Tax chanceCard from the given
	 * information.
	 * 
	 * @param i
	 *            The information given.
	 * @return The Tax chanceCard.
	 */
	private TaxCard createTaxCard(String[] i) {
		String[] stringArray = { i[2], i[3] };
		TaxCard taxCard = new TaxCard(i[0], i[1], toIntArray(stringArray));
		return taxCard;
	}

	/**
	 * Method createPayment: Returns a Payment chanceCard from the given
	 * information.
	 * 
	 * @param i
	 *            The information given.
	 * @return The Payment chanceCard.
	 */
	private Payment createPayment(String[] i) {
		Payment payment = new Payment(i[0], i[1], toInt(i[2]));
		return payment;
	}

	/**
	 * Method createParty: Returns a Party chanceCard from the given
	 * information.
	 * 
	 * @param i
	 *            The information given.
	 * @return The Party chanceCard.
	 */
	private Party createParty(String[] i) {
		Party party = new Party(i[0], i[1], toInt(i[2]));
		return party;
	}

	/**
	 * Method createPrison: Returns a Prison chanceCard from the given
	 * information.
	 * 
	 * @param i
	 *            The information given.
	 * @return The Prison chanceCard.
	 */
	private Prison createPrison(String[] i) {
		Prison prison = new Prison(i[0], i[1]);
		return prison;
	}

	/**
	 * Method createMoveThreeSteps: Returns a MoveThreeSteps chanceCard from the
	 * given information.
	 * 
	 * @param i
	 *            The given information.
	 * @return The MoveThreeSteps chanceCard.
	 */
	private MoveThreeSteps createMoveThreeSteps(String[] i) {
		MoveThreeSteps moveThreeSteps = new MoveThreeSteps(i[0], i[1], toInt(i[2]));
		return moveThreeSteps;
	}

	/**
	 * Method createMoveToField: Returns a MoveToField chanceCard from the given
	 * information.
	 * 
	 * @param i
	 *            The given information.
	 * @return The MoveToField chanceCard.
	 */
	private MoveToField createMoveToField(String[] i) {
		MoveToField moveToField = new MoveToField(i[0], i[1], toInt(i[2]));
		return moveToField;
	}

	/**
	 * Method createMoveToPrison: Returns a MoveToPrison chanceCard from the
	 * given information.
	 * 
	 * @param i
	 *            The given information.
	 * @return The MoveToPrison chanceCard.
	 */
	private MoveToPrison createMoveToPrison(String[] i) {
		MoveToPrison moveToPrison = new MoveToPrison(i[0], i[1]);
		return moveToPrison;
	}

	/**
	 * Method createMoveToNearestShipping: Returns a MoveToNearestShipping
	 * chanceCard from the given information.
	 * 
	 * @param i
	 *            The given information.
	 * @return The MoveToNearestShipping chanceCard.
	 */
	private MoveToNearestShipping createMoveToNearestShipping(String[] i) {
		String[] stringArray = { i[2], i[3], i[4], i[5] };
		MoveToNearestShipping moveToNearestShipping = new MoveToNearestShipping(i[0], i[1], toIntArray(stringArray),
				toBoolean(i[6]));
		return moveToNearestShipping;
	}

	/**
	 * Method toInt: Converts a string to an integer.
	 * 
	 * @param s
	 *            The given String
	 * @return The returned integer.
	 */
	private int toInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * Method toBoolean: Converts a string to a boolean.
	 * 
	 * @param s
	 *            The given String.
	 * @return The returned boolean.
	 */
	private boolean toBoolean(String s) {
		return Boolean.parseBoolean(s);
	}

	/**
	 * Method toIntArray: Converts a String array to an integer array.
	 * 
	 * @param s
	 *            The String array.
	 * @return The returned integer array.
	 */
	private int[] toIntArray(String... s) {
		int[] intArray = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			intArray[i] = toInt(s[i]);
		}
		return intArray;
	}
}
