package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.GUICreator;
import entity.GameBoard;

public class Reader {

	BufferedReader reader = null;

	/**
	 * Method openFieldData: Open the file, throw an exception if it dosent
	 * exist.
	 */
	public void openFieldData() {
		try {
			reader = new BufferedReader(new FileReader("src/data/Feltliste.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File can not be opened\n");
		}
	}

	/**
	 * Method readFields: Read all the field from a file, and add them to the
	 * GUI and the gameboard.
	 * 
	 * @param board
	 *            The gameboard that holds all the Fields.
	 * @param gui
	 *            A GUICreator that construct the board on the GUI.
	 */
	public void readFields(GameBoard board, GUICreator gui) {
		// initialize the board buidilng
		String[] information = null;
		gui.beginBoardBuilding();

		// For every field on the board.
		for (int i = 0; i < 40; i++) {
			if (reader != null) {
				try {
					// Reads the next line and splits it into a String array.
					information = reader.readLine().split(";");
				} catch (IOException e) {
				}
			}
			// Add the field to the GUI.
			gui.addField(information);

			// Add the field to the Gameboard.
			// board.addField(information);
		}
		// End the board Building.
		gui.endBoardBuilding();

	}

	/**
	 * Method closeFile: Close the file.
	 */
	public void closeFile() {
		try {
			reader.close();
		} catch (IOException e) {

		}
	}
}
