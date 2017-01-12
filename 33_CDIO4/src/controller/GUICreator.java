package controller;

import java.awt.Color;

import data.Reader;
import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Tax;
import desktop_resources.GUI;

/**
 * This class is responsible for creating the GUI.
 * 
 * @author Gruppe33
 *
 */
public class GUICreator {

	// Instance variables
	private Car[] cars;
	private Field[] fields;
	private int fieldCounter;

	/**
	 * GUICreator constructor
	 */
	public GUICreator() {
		Reader dataReader = new Reader("src/data/Feltliste.txt");
		String[][] data = dataReader.readFile();
		beginBoardBuilding();
		for (int i = 0; i < 40; i++) {
			addField(data[i]);
		}
		endBoardBuilding();
	}

	/**
	 * Method beginBoardbuidling: Create a list of GUI Fields. This should be
	 * used before the addField Method.
	 *
	 */
	public void beginBoardBuilding() {
		fields = new Field[40];
		fieldCounter = 1;
	}

	/**
	 * This method constructs the fields on the GUI, after they have been added
	 * by the addField method.
	 */
	public void endBoardBuilding() {
		GUI.create(fields);
		cars = createCars();
	}

	/**
	 * Method addField: Adds a field to the GUI Game board
	 * 
	 * @param information
	 *            Array of all the field information. This information depends
	 *            on the field in question. This method calls many different
	 *            submethods using a switch. These methods construct the
	 *            different types of fields.
	 */
	public void addField(String[] information) {
		// Gets the type of the fields.
		String fieldType = information[information.length - 2];

		// Calls the respective method that handles specific types of fields.
		switch (fieldType) {
		case "Ejendom":
			addStreet(information);
			break;
		case "Rederi":
			addShipping(information);
			break;
		case "Tapperi":
			addBrewery(information);
			break;
		case "Chancekort":
			addChance(information);
			break;
		case "Skat":
			addTax(information);
			break;
		case "Start":
			addStart(information);
			break;
		case "Fængsel":
			addJail(information);
			break;
		case "Besøg":
			addVisit(information);
			break;
		case "Parkering":
			addParking(information);
			break;
		default:
			// Throws an error message when the field type is not regonised.
			System.out.println("GUICreator: Not a valid field at field number: " + fieldCounter);
		}
		fieldCounter++;
	}

	/**
	 * Method addStreet: Adds a Street field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addStreet(String[] fieldData) {
		Color color = getFieldColor(fieldData[1]);
		fields[fieldCounter - 1] = new Street.Builder().setTitle(fieldData[0]).setSubText(fieldData[2])
				.setDescription("").setBgColor(color).setRent(fieldData[3]).build();
	}

	/**
	 * Method addShipping: Adds a Shipping field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addShipping(String[] fieldData) {
		fields[fieldCounter - 1] = new Shipping.Builder().setTitle(fieldData[0]).setSubText(fieldData[2])
				.setDescription(fieldData[1])
				.setBgColor(
						Color.getHSBColor((float) (216.21 / 360.0), (float) (72.5 / 100.0), (float) (62.75 / 100.0)))
				.setPicture("src/data/pictures/Ferry.png").build();
	}

	/**
	 * Method addBrewery: Adds a Brewery field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addBrewery(String[] fieldData) {
		fields[fieldCounter - 1] = new Brewery.Builder().setTitle(fieldData[0]).setSubText(fieldData[2])
				.setDescription(fieldData[1]).setBgColor(Color.BLACK).build();
	}

	/**
	 * Method addChance: Adds a chanceCard field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addChance(String[] fieldData) {
		fields[fieldCounter - 1] = new Chance.Builder().setBgColor(Color.LIGHT_GRAY).build();
	}

	/**
	 * Method addTax: Adds a Tax field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addTax(String[] fieldData) {
		fields[fieldCounter - 1] = new Tax.Builder().setTitle(fieldData[0]).setSubText(fieldData[2])
				.setDescription(fieldData[1]).setBgColor(Color.LIGHT_GRAY).build();
	}

	/**
	 * Method addStart: Adds a Start field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addStart(String[] fieldData) {
		fields[fieldCounter - 1] = new Start.Builder().setTitle(fieldData[0]).setSubText(fieldData[2])
				.setDescription(fieldData[1]).setBgColor(Color.RED).build();
	}

	/**
	 * Method addJail: Adds a Go To Jail field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addJail(String[] fieldData) {
		fields[fieldCounter - 1] = new Jail.Builder().setSubText(fieldData[0]).setDescription(fieldData[1])
				.setBgColor(Color.GRAY).build();
	}

	/**
	 * Method addVisit: Adds a Visit Jail field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addVisit(String[] fieldsData) {
		fields[fieldCounter - 1] = new Jail.Builder().setSubText(fieldsData[0]).setDescription(fieldsData[1])
				.setBgColor(Color.GRAY).build();
	}

	/**
	 * Method addParking: Adds a Parking field to the GUI's fields array.
	 * 
	 * @param fieldData
	 *            String array of all the fields data.
	 */
	private void addParking(String[] fieldsData) {
		fields[fieldCounter - 1] = new Refuge.Builder().setSubText(fieldsData[0]).setDescription(fieldsData[1])
				.setBgColor(
						Color.getHSBColor((float) (198.1 / 360.0), (float) (100.0 / 100.0), (float) (90.98 / 100.0)))
				.setPicture("src/data/pictures/Parkeringslogo.png").build();
	}

	/**
	 * Method addPlayersToGUI: Adds the players to the GUI.
	 * 
	 * @param players
	 *            String array of player names
	 */
	private void addPlayersToGUI(String[] players) {
		for (int i = 0; i < players.length; i++) {
			GUI.addPlayer(players[i], 30000, cars[i]);
			GUI.setCar(1, players[i]);
		}
	}

	/**
	 * Method askNumberOfPlayers: Lets the player insert how many players are
	 * participating in the game.
	 * 
	 * @return (int) The number of players that are playing.
	 */
	private int askNumberOfPlayers() {
		// Ask the players how many are playing
		int numberOfPlayers = GUI.getUserInteger("Indtast hvor mange spillere 2-6", 2, 6);
		return numberOfPlayers;
	}

	/**
	 * Method getPlayerNames: Let all the players enter their player names.
	 * 
	 * @return String array of the player names.
	 */
	public String[] getPlayerNames() {
		int numberOfPlayers = askNumberOfPlayers();
		String[] playerNames = new String[numberOfPlayers];

		// Lets player 1 enter a name that is not an empty String.
		do {
			playerNames[0] = GUI.getUserString("Indtast navn for spiller nummer 1");
		} while (playerNames[0].equals(""));

		// Lets all the users insert their player names
		for (int i = 1; i < numberOfPlayers; i++) {
			boolean nameEqual = true;
			playerNames[i] = null;

			while (nameEqual) {
				// Lets player 2-6 enter a name that is not an empty String.
				do {
					playerNames[i] = GUI.getUserString("Indtast navn for spiller nummer " + (i + 1));
				} while (playerNames[i].equals(""));

				// Goes through the already added player names
				for (int j = 0; j < i; j++) {

					// If the entered player name matches an already existing
					// name
					if (playerNames[j].equals(playerNames[i])) {
						GUI.getUserButtonPressed("Navnet findes allerede, vælg venligst et andet.", "OK");
						nameEqual = true;
						break;
					} else {
						nameEqual = false;
					}
				}
			}
		}
		addPlayersToGUI(playerNames);
		return playerNames;
	}

	/**
	 * Method createCars builds all the available cars in the game.
	 * 
	 * @return Car array of all the cars.
	 */
	private Car[] createCars() {
		Car[] carArray = new Car[6];
		carArray[0] = new Car.Builder().primaryColor(Color.BLUE).secondaryColor(Color.BLACK).typeUfo().patternFill()
				.build();
		carArray[1] = new Car.Builder().primaryColor(Color.RED).secondaryColor(Color.BLACK).typeUfo().patternFill()
				.build();
		carArray[2] = new Car.Builder().primaryColor(Color.GREEN).secondaryColor(Color.BLACK).typeTractor()
				.patternFill().build();
		carArray[3] = new Car.Builder().primaryColor(Color.YELLOW).secondaryColor(Color.BLACK).typeTractor()
				.patternFill().build();
		carArray[4] = new Car.Builder().primaryColor(Color.PINK).secondaryColor(Color.BLACK).typeRacecar().patternFill()
				.build();
		carArray[5] = new Car.Builder().primaryColor(Color.WHITE).secondaryColor(Color.BLACK).typeRacecar()
				.patternFill().build();
		return carArray;
	}

	/**
	 * Method getFieldColor: Gets the color of the field
	 * 
	 * @param fieldColor
	 *            String describing the color in danish.
	 * @return The corresponding Color object.
	 */
	private Color getFieldColor(String fieldColor) {
		Color color;
		switch (fieldColor) {
		case "Rød":
			color = Color.RED;
			break;
		case "Blå":
			color = Color.BLUE;
			break;
		case "Orange":
			color = Color.ORANGE;
			break;
		case "Grøn":
			color = Color.GREEN;
			break;
		case "Grå":
			color = Color.GRAY;
			break;
		case "Hvid":
			color = Color.WHITE;
			break;
		case "Gul":
			color = Color.YELLOW;
			break;
		case "Lilla":
			color = Color.MAGENTA;
			break;
		default:
			color = Color.GRAY;
			break;
		}
		return color;
	}
}
