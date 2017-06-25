package semoutputmanager;

import semdata.ReleaseHistory;

public class ReportFactory {

	public ReportFactory(){
		
	}
	
	public ReportWriter createOutputWriter(String userChoice,
											ReleaseHistory project){
		if( userChoice == "Txt")
			return new TxtReportWriter(project);
		else
			return new HtmlReportWriter(project);
		
	}

}
