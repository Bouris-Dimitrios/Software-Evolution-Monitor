package semoutputmanager;

import semdata.LehmanLaw;
import semdata.ReleaseHistory;

public class HtmlReportWriter extends ReportWriter {
	
	public HtmlReportWriter(ReleaseHistory releaseHistory){
		super(releaseHistory);
	}
	protected  void writeBanner(){
		fileWriter.println(" <h1><font color=\"Black\"> Lehman Law Report </font></h1>");
	}
	
	protected void writeProjectName(){
		fileWriter.println("<p><font size=\"4\">Project Name: </font><font color=\"red\" size=\"5\">"+
				releaseHistory.getName() + "</font></p>");
	}
	protected void createFileName(String projectName) {
		fileName = projectName + "Report.html";
	}
	
	protected void writeDetailsForSpecificLhemanLaw(LehmanLaw law){
			writeTag("tr");changeLine();
			writeTag("td");
			fileWriter.print("Lehman Law " + law.getLawId() + " : " + law.getLawName() + ".");
			writeClosingTag("td");changeLine();
			writeTag("td");
			fileWriter.print(law.getUserDecision());
			writeClosingTag("td");changeLine();
			writeTag("td");
			fileWriter.print(law.getUserComment());
			writeClosingTag("td");	changeLine();		
			writeClosingTag("tr");changeLine();	
	}
	
	
	private void writeTag(String tag){
		fileWriter.print("<" + tag + ">");
	}

	private void writeClosingTag(String tag){
		fileWriter.print("</" + tag + ">");
	}
	private void writeStyle(){
		writeTag("style");changeLine();
		fileWriter.println("\ttable{\n\t\tborder-collapse:collapse;\n\t}\n\t\t\t");
		fileWriter.println("\ttable, td ,th{\n\t\tborder: 1px solid black;\n\t\t"
				+ "border-width: 1px;\n\t\tborder-style: groovie;\n\t\t"
				+ "border-spacing 1;\n\t\tfont-size: 1em\n\t}\n\ttd{\n\t\t"
				+ "padding: 15px;\n\t\tcolor: red;\n\t\t"
				+ "text-align:left;\n\t}\n\t")	;
		writeClosingTag("style");changeLine();
	}
	
	private void writeHeadPart(){
		writeTag("!DOCTYPE html");changeLine();
		writeTag("html");changeLine();
		writeTag("head");changeLine();
		writeTag("title");
		fileWriter.print("Software Evolution Monitor Report: "
				+ releaseHistory.getName());
		writeClosingTag("title");changeLine();
		writeTag("meta charset = \"UTF-8:\" ");changeLine();
		writeStyle();
		writeClosingTag("head");changeLine();		
	}

	private void writeTable(){
		fileWriter.println("<table class=\"sample\">");
		writeTag("tr");changeLine();

		fileWriter.println("<th><font  size =\"5\" color=\"blue\"> Law Name </font></th>"
				+ "<th><font  size =\"5\" color=\"blue\"> User Decision</font ></th>"
				+ "<th><font size =\"5\" color=\"blue\"> User Comment </font></th> ");
		writeClosingTag("tr");changeLine();
		writeDetailsAboutEvaluatedLaws();
	}
	
	private void writeBodyPart(){
		writeTag("body  style = \"background-color:Lavender \" ");changeLine();
		writeBanner();
		writeProjectName();
		writeTable();
		writeClosingTag("body");changeLine();
		writeClosingTag("html");changeLine();
	}
	
	public void writeReportForReleaseHistory(){
		 writeHeadPart();
		 writeBodyPart();
		 fileWriter.close();
	}
}
