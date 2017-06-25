package semmainengine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;

import semdata.LehmanLaw;
import semdata.ReleaseHistory;
import semgraphvisualizer.GraphManager;
import seminputmanager.InputParser;
import seminputmanager.TxtParser;
import semoutputmanager.ReportFactory;
import semoutputmanager.ReportWriter;

public class MainEngine {
	private GraphManager graphManager;
	private List<ReleaseHistory> projects;
	private ReportWriter outputWriter;
	private InputParser inputReader;
	private ReleaseHistory currentProject;
	
	private boolean checkIfProjectNamesIsEqual(String projectName1,
												String projectName2){
		if(projectName1.compareTo(projectName2) == 0)
			return true;
		return false;
	}	

	private boolean areLawsTwoAndSixEvaluated(){
		return currentProject.isLawAlreadyEvaluated(2) 
				&& currentProject.isLawAlreadyEvaluated(6);
	}
	
	private void findProjectEqualWith(String projectName){
		for(int i=0; i < projects.size(); i++){
			if(checkIfProjectNamesIsEqual(projectName,projects.get(i).getName()))
				currentProject = (ReleaseHistory) projects.get(i);
		}		
	}	
	
	public void initOutputWriter(String outputWriterType){
		ReportFactory reportFactory = new ReportFactory();
		outputWriter = reportFactory.createOutputWriter(outputWriterType, 
				currentProject);
	}
	
	public String getLawDecisionForCurrentProject(int lawId){
		return currentProject.getLawDecision(lawId);
	}
	
	public MainEngine(){
		projects = new ArrayList<ReleaseHistory>();
	}
	
	public String getCurrentProjectName(){
		return currentProject.getName();
	}
	
	public int readFile(){
		currentProject = inputReader.parseReleaseHistoryFromFile();
		if(projectIsAlreadyLoaded(inputReader.getProjectName()))
			return 1;
		if( ! inputReader.isFileFormatCorrect() )
			return 2;
		projects.add(currentProject);
		return 0;
	}
	
	public boolean projectIsAlreadyLoaded(String projectName){
		for(int i = 0; i < projects.size(); i++){
			if( projectName.equals(projects.get(i).getName()) )
				return true;
		}
		return false;
	}
	
	public boolean initializeInputParser(File file){
		if( file.getName().endsWith(".txt")){
			inputReader = new TxtParser(file.getAbsolutePath());			
			return true;
		}
		return false;
	}
	
	public void writeReportForCurrentProject(){
		outputWriter.writeReportForReleaseHistory();
	}

	public String[] getProjectNames(){
		ReleaseHistory project;
		String [] projectNames ;

		projectNames = new String[projects.size()];
		for(int i = 0; i < projects.size(); i++){
			project = (ReleaseHistory) projects.get(i);
			projectNames[i] = project.getName();
		}
		return projectNames;
	}
	
	public List <JFreeChart> getGraphsForSpecificLaw(int lawId){
		graphManager = new GraphManager(currentProject.getVersions());
		return graphManager.computeChartsForASpecificLaw(lawId);
	}
	
	public boolean isLawSevenEvaluatable(){
		return areLawsTwoAndSixEvaluated();
	}
	
	public void chooseCurrentProjectAndComputeMetrics(String usersChoosenProject){
		findProjectEqualWith(usersChoosenProject);
		currentProject.computeMetricsForAllVersions();
	}
	
	public void saveLehmanLawEvaluation(int lawId,String userDecision,String userComment, String lawName){
		LehmanLaw law = new LehmanLaw(lawId,userDecision,userComment,lawName);
		currentProject.addLehmanLaw(law);
	}
}
