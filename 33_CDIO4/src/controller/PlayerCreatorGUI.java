package controller;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;

public class PlayerCreatorGUI {
	private Car[] cars;
	
	public PlayerCreatorGUI(){
		cars=createCars();
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
		int numbPlayer=askNumberOfPlayers();
		String[] playerNames;
		playerNames = new String[numbPlayer];
		do {		//Small do-while loop to check if the first player has entered an empty name, i.e. an empty string.
			playerNames[0] = GUI.getUserString("Indtast navn for spiller nummer 1");
		} while (playerNames[0].equals(""));


		for (int i = 1; i < numbPlayer; i++) {
			boolean nameEqual = true;
			playerNames[i] = null;

			while (nameEqual) {
				do {
					playerNames[i] = GUI.getUserString("Indtast navn for spiller nummer"+(i+1));
				} while (playerNames[i].equals(""));
				
				for (int j = 0; j < i; j++) {
					if (playerNames[j].equals(playerNames[i]) || playerNames[i].equals("")) {		//Another check to see if one the other players enter an empty string.
						GUI.getUserButtonPressed("Spiller navnet er allerede indtastede, vælg et andet spillernavn.", "OK");
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
		carArray[4] = new Car.Builder().primaryColor(Color.PINK).secondaryColor(Color.BLACK).typeRacecar()
				.patternFill().build();
		carArray[5] = new Car.Builder().primaryColor(Color.WHITE).secondaryColor(Color.BLACK).typeRacecar()
				.patternFill().build();
		return carArray;
	}
}
