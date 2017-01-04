package controller;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_resources.GUI;

public class GUICreator {
	private Car[] cars;
	private Field[] fields;
	private int fieldCounter;

	public GUICreator() {

	}

	public void beginBoardBuilding() {
		fields = new Field[40];
		fieldCounter = 1;
	}

	public void addField(String[] information) {
		String fieldType = information[information.length - 2];
		System.out.println(fieldType);
		switch (fieldType) {
		case "Ejendom":
			addProperty(information);
			break;
		case "Rederi":
			addFleet(information);
			break;
		case "Tapperi":
			addBrewery(information);
			break;	
		case "Chancekort":
			addChanceField(information);
			break;	
		default:
			System.out.println("Not a valid field at field number: " + fieldCounter);
		}
		fieldCounter++;
	}

	public void addProperty(String[] information) {
		Color color = getColorFromField(information[1]);
		fields[fieldCounter - 1] = new Street.Builder().setTitle(information[0]).setSubText(information[2])
				.setDescription("").setBgColor(color).build();
	}

	public void addFleet(String[] information) {
		fields[fieldCounter - 1] = new Shipping.Builder().setTitle(information[0]).setSubText(information[2])
				.setBgColor(Color.BLUE).build();
	}
	public void addBrewery(String[] information) {
		fields[fieldCounter - 1] = new Brewery.Builder().setTitle(information[0]).setSubText(information[2])
				.setBgColor(Color.BLACK).build();
	}
	public void addChance(String[] information) {
		fields[fieldCounter - 1] = new Chance.Builder()
				.setBgColor(Color.GRAY).build();
	}
	

	public void endBoardBuilding() {
		GUI.create(fields);
		cars = createCars();
		GUI.setDice(1, 1);
	}

	private void addPlayersToGUI(String[] players) {
		for (int i = 0; i < players.length; i++)
			GUI.addPlayer(players[i], 30000, cars[i]);
	}

	/**
	 * Method that prompts the user to enter how many players will be playing.
	 * 
	 * @return The number of players that want to play.
	 */
	private int askNumberOfPlayers() {
		// Ask the players how many are playing
		int numbPlayer = 0;
		numbPlayer = GUI.getUserInteger("Indtast hvor mange spillere 2-6", 2, 6);
		return numbPlayer;
	}

	/**
	 * Method that prompts the users to enter their playernames. It also
	 * prevents them from typing the same names.
	 * 
	 * @param numbPlayer
	 *            The number of players that want to play the game.
	 * @return Player Names.
	 */
	public String[] getPlayerNames() {
		int numbPlayer = askNumberOfPlayers();
		String[] playerNames;
		playerNames = new String[numbPlayer];
		do { // Small do-while loop to check if the first player has entered an
				// empty name, i.e. an empty string.
			playerNames[0] = GUI.getUserString("Indtast navn for spiller nummer 1");
		} while (playerNames[0].equals(""));

		for (int i = 1; i < numbPlayer; i++) {
			boolean nameEqual = true;
			playerNames[i] = null;

			while (nameEqual) {
				do {
					playerNames[i] = GUI.getUserString("Indtast navn for spiller nummer" + (i + 1));
				} while (playerNames[i].equals(""));

				for (int j = 0; j < i; j++) {
					if (playerNames[j].equals(playerNames[i]) || playerNames[i].equals("")) {
						GUI.getUserButtonPressed("Spiller navnet er allerede indtastede, vælg et andet spillernavn.",
								"OK");
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

	private Color getColorFromField(String colorInformation) {
		Color color;
		switch (colorInformation) {
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
		case "Lilla":
			color = Color.MAGENTA;
		default:
			color = Color.GRAY;
			break;
		}
		return color;
	}
}
