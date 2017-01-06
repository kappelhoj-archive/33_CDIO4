package test;

import controller.GUICreator;
import data.Reader;
import entity.GameBoard;


public class RunningReaderTest {
	
	public static void main(String[] args) {
//		GUICreator gui=new GUICreator();
//		GameBoard gameBoard=new GameBoard();
//		Reader reader = new Reader();
//		reader.openFieldData();
//		reader.readFields(gameBoard,gui);
		Reader reader = new Reader("src/data/Feltliste.txt");
//		reader.openFile("src/data/Feltliste.txt");
		String[][] fileData = reader.readFile();
		System.out.print("{ ");
		for (int i=0;i<fileData.length; i++) {
			System.out.print(fileData[i]);
			if(i != fileData.length-1)
				System.out.print(", ");
		}
		System.out.print(" }");
//		String[] splitData = new String[12];
//		String[][] allData = new String[fileData.length][splitData.length];
//		
//		for (int i = 0; i < fileData.length; i++) 
//		{
//			
//			for (int j = 0; j < splitData.length; j++)
//			{
//				allData[i] = fileData[i].split(";");
//			}
//		}
//		splitData = fileData[0].split(";");
//		for (int k = 0; k < splitData.length; k++)
//		{
//			System.out.println(splitData[k]);
//		}
		
	}
}
