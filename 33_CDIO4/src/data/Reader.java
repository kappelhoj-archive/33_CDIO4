package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
/**
 * This class is responsible for reading the fields from a file.
 * @author Gruppe33
 *
 */
public class Reader {
	
	//Instance variables
	File file;
	
	/**
	 * Constructor: Constructs a Reader.
	 * @param fileName The file to be read.
	 */
	public Reader(String fileName)
	{
		file = new File(fileName);
	}
	
	/**
	 * Method readFile: Returns the information in the file as a 2-dimensional String array.
	 * @return The 2-dimensional String array.
	 */
	public String[][] readFile()
	{
		String[][] fileData = new String[40][12];
		try {
			Scanner scanner = new Scanner(file);
			
			for(int i = 0; i < 40; i++)
			{
				fileData[i] = scanner.nextLine().split(";");
			}
			scanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileData;
	}
	
	/**
	 * Method formatFileData: Returns the data without '.'.
	 * @param fieldData The data to be formatted.
	 * @return Returns the formatted data.
	 */
	public String[][] formatFileData(String[][] fieldData)
	{
		String[][] formattedFieldData = new String[fieldData.length][fieldData[1].length];
		for(int i = 0; i < fieldData.length; i++)
		{
			for(int j = 0; j < fieldData[i].length; j++)
			{
				formattedFieldData[i][j] = fieldData[i][j].replaceAll("\\.", "");
			}	
		}
		return formattedFieldData;
	}
}
