package test;

import controller.GUICreator;
import data.Reader;
import entity.GameBoard;


public class RunningReaderTest {
	
	public static void main(String[] args) {
		GUICreator gui=new GUICreator();
		GameBoard gameBoard=new GameBoard();
		Reader reader = new Reader();
		reader.openFieldData();
		reader.readFields(gameBoard,gui);
	}
}
