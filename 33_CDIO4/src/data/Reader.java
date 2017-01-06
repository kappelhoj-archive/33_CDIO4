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
}
