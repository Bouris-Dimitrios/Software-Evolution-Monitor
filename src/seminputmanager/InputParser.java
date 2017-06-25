package seminputmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import semdata.ReleaseHistory;
import semdata.Version;

public abstract class InputParser {
	protected BufferedReader bufferedReader;
	protected ReleaseHistory releaseHistory;
	protected String fileName;
	protected String currentLine;
	protected boolean correctFormat;
	
	protected InputParser(String fileName){
	    try {
	    	this.fileName = fileName;
	    	bufferedReader = new BufferedReader(new FileReader(fileName));
	    	correctFormat = true;

	    } catch(Exception exception) {
	    	System.out.println(exception.getMessage());
	    	correctFormat = false;
	    }
	}
	
	protected int parseIntFromString(String number){
		try{
			return Integer.parseInt(number);
		}catch(Exception exception){
			System.out.println(exception.getMessage());
	    	correctFormat = false;
	    	return - 9999;
		}
	}
	
	public boolean isFileFormatCorrect(){
		return correctFormat;
	}
	
	public ReleaseHistory parseReleaseHistoryFromFile(){
    	releaseHistory = new ReleaseHistory(readName(),
    						readInitialNumberOfOperations(),
    						readInitialNumberOfDataStructures());
    	readEmptyLine();
    	readEmptyLine();
		readVersions();
		return releaseHistory;
	}
	
	protected  Date parseDate(String aDateInTxtFormat){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		try {
			Date date = format.parse(aDateInTxtFormat);
			return date;
		} catch (ParseException e) {
			correctFormat = false;
			e.printStackTrace();
		}		
		return new Date();
	}
	
	public String getProjectName(){
		return releaseHistory.getName();
	}
	
	protected void readLine() {
		try {
			currentLine = bufferedReader.readLine();
		} catch (Exception exceptionHandler) {
			correctFormat = false;
			System.out.println("Exception : " + exceptionHandler.getMessage());
		}
	}
	
	protected String removeTokenFromLine(String tokenToBeRemoved) {
		return currentLine.replaceAll(tokenToBeRemoved, "");
	}
	
	private String removeWhiteSpaces(String stringToRemoveWhiteSpaces) {
		return stringToRemoveWhiteSpaces.trim();
	}	
	
	protected int readIntegerFieldFromFile(String tagToBeRemoved){
		String stringWithRemovedToken;
		try {
			readLine();
			stringWithRemovedToken = removeTokenFromLine(tagToBeRemoved);
			stringWithRemovedToken = removeWhiteSpaces(stringWithRemovedToken);
			return Integer.parseInt(stringWithRemovedToken);
		} catch (Exception exceptionHandler) {
			System.out.println("Exception : " + exceptionHandler.getMessage());
			correctFormat = false;
		}
		return 1;
	}
	
	protected void readVersions(){
		
		readLine();
		while(currentLine != null){
			releaseHistory.addVersion(parseVersionFromLine());
			readLine();
		}
		return ;
		
	}
	protected void readEmptyLine(){
		readLine();
	}
	
	abstract public String readName();
	abstract protected int readInitialNumberOfOperations();
	abstract protected int readInitialNumberOfDataStructures();	
	abstract protected Version parseVersionFromLine();
}
