package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Reader {
	
	File file;
	
	public Reader(String fileName)
	{
		file = new File(fileName);
	}
	
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
