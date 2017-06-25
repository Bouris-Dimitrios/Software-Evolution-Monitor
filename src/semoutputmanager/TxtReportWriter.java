package semoutputmanager;

import semdata.LehmanLaw;
import semdata.ReleaseHistory;

public class TxtReportWriter extends ReportWriter {
	
	public TxtReportWriter(ReleaseHistory releaseHistory){
		super(releaseHistory);
	}
	public void writeReportForReleaseHistory(){
		writeBanner();
		writeProjectName();
		fileWriter.println("Below are user's Evaluations for each Lehman Law: ");
		writeDetailsAboutEvaluatedLaws();
		fileWriter.close();
	}	
	
	protected  void writeBanner(){
		fileWriter.println("- - - - - - - - - - - - - - - - - - - -");
		fileWriter.println("- Software Evolution Monitor Report.  -");
		fileWriter.println("- - - - - - - - - - - - - - - - - - - -");
		changeLine();
		
		
	}	
	protected void writeProjectName(){
		fileWriter.println("Project Name : " + releaseHistory.getName() + ".");
		changeLine();
	}
	protected void writeDetailsForSpecificLhemanLaw(LehmanLaw law){
		fileWriter.println("Lehman Law " + law.getLawId() + " : " + law.getLawName() + ".");
		fileWriter.println("User's Decision: " + law.getUserDecision() + ".");
		fileWriter.println("User's Comment: " + law.getUserComment() + ".");
		changeLine();
	}
	protected void createFileName(String projectName) {
		fileName = projectName + "Report.txt";
	}

}
