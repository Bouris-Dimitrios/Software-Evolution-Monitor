package seminputmanager;

import java.util.Date;


import semdata.ChangesHolder;
import semdata.Version;

public class TxtParser extends InputParser{
	
	
	public String readName(){
		readLine();
		return removeTokenFromLine("Name;");
	}
	
	protected int  readInitialNumberOfOperations(){
		return readIntegerFieldFromFile("Initial Number of Operations;");
	}
	
	protected int readInitialNumberOfDataStructures(){
		return readIntegerFieldFromFile("Initial Number of Data Structures;");
	}
	
	protected Version parseVersionFromLine(){
		try{
			String [] versionAttributes = currentLine.split(";");
			int id = Integer.parseInt(versionAttributes[0]);
			Date date = parseDate(versionAttributes[1]);
			ChangesHolder operationChanges = new ChangesHolder(
					parseIntFromString(versionAttributes[2]),
					parseIntFromString(versionAttributes[3]),
					parseIntFromString(versionAttributes[4]));
	
			ChangesHolder dataStructureChanges = new ChangesHolder(
					parseIntFromString(versionAttributes[5]),
					parseIntFromString(versionAttributes[6]),
					parseIntFromString(versionAttributes[7]));
			return new Version(id,date,operationChanges,dataStructureChanges); 
		}
		catch(Exception exceptionHandler){
			System.out.println("Exception : " + exceptionHandler.getMessage());
			correctFormat = false;
			return null;
		}
	}
	
	public TxtParser(String fileName){
		super(fileName);
	}
	
	
	
	
}
