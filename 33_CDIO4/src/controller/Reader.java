package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import entity.GameBoard;

public class Reader {

	BufferedReader reader = null;

	public void openFile(String file) {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File can not be opened");
		}
	}

	public void readFields(GameBoard board, GUICreator gui) {
		String[] information = null;
		if (reader != null) {
			try {
				information = reader.readLine().split(" ");
			} catch (IOException e) {
			}
		}
		
		for(int i=0;i<information.length;i++){
			System.out.println(information[i]);
		}
		
		//Indsæt GameBoard Function her.
		
		//Indsæt GUI Funktion her.
	}

	public void closeFile() {
		try {
			reader.close();
		} catch (IOException e) {

		}
	}
}
