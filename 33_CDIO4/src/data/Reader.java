package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.GUICreator;
import entity.GameBoard;

public class Reader {

	BufferedReader reader = null;

	public void openFieldData() {
		try {
			reader = new BufferedReader(new FileReader("src/data/Feltliste.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File can not be opened\n");
		}
	}

	public void readFields(GameBoard board, GUICreator gui) {
		String[] information = null;
		gui.beginBoardBuilding();
		for (int i = 0; i < 40; i++) {
			if (reader != null) {
				try {
					information = reader.readLine().split(";");
				} catch (IOException e) {
				}
			}
			
			gui.addField(information);
			board.addField(information);
		}
		gui.endBoardBuilding();
		




	}

	public void closeFile() {
		try {
			reader.close();
		} catch (IOException e) {

		}
	}
}
