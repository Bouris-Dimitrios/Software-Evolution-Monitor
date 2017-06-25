package semoutputmanager;
import java.io.PrintWriter;
import java.util.HashMap;

import semdata.LehmanLaw;
import semdata.ReleaseHistory;

public abstract class ReportWriter {
	protected PrintWriter fileWriter;
	protected String fileName;
	protected ReleaseHistory releaseHistory;
	
	protected abstract void createFileName(String projectName);
	protected abstract void writeProjectName();
	protected abstract void writeBanner();
	public abstract void writeReportForReleaseHistory();
	protected abstract void  writeDetailsForSpecificLhemanLaw(LehmanLaw law);
	private void  openFile(){
		try {
			fileWriter = new PrintWriter(fileName, "UTF-8");
		} catch (Exception exceptionHandler) {
			System.out.println("Exception : " + exceptionHandler.getMessage());
		}
	}
	
	protected void changeLine(){
		fileWriter.println();
	}
	
	public ReportWriter(ReleaseHistory releaseHistory){
		this.releaseHistory = releaseHistory;
		createFileName(releaseHistory.getName());
		openFile();
		//writeReportForReleaseHistory();
		
	}
	

	public void writeDetailsAboutEvaluatedLaws(){
		HashMap<Integer,LehmanLaw> evaluatedLehmanLaws =releaseHistory.
				getEvaluatedLehmanLaws();
		//fileWriter.println("Below are user's Evaluations for each Lehman Law: ");
		changeLine();
		for(int i = 1 ; i <=8 ; i ++)
			if(evaluatedLehmanLaws.containsKey(i))
				writeDetailsForSpecificLhemanLaw(evaluatedLehmanLaws.get(i));
	}

}
