package semdata;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class ReleaseHistory {
	private String projectName;
	private int initialNumberOfOperations;
	private int initialNumberOfDataStructures;
	private List<Version> versions;
	private HashMap<Integer,LehmanLaw> evaluatedLehmanLaws;
	
	public ReleaseHistory(String name,int initialNumberOfOperations,
			int initialNumberOfDataStructures){
		this.projectName = name;
		this.initialNumberOfOperations = initialNumberOfOperations;
		this.initialNumberOfDataStructures = initialNumberOfDataStructures;
		versions = new ArrayList<Version>();
		evaluatedLehmanLaws = new HashMap<Integer,LehmanLaw>();
	}
	
	public boolean isLawAlreadyEvaluated(LehmanLaw evaluatedLaw){
		return evaluatedLehmanLaws.containsKey(evaluatedLaw.getLawId());
	}
	
	public boolean isLawAlreadyEvaluated(int lawId){
		return evaluatedLehmanLaws.containsKey(lawId);
	}
	
	public HashMap<Integer,LehmanLaw>  getEvaluatedLehmanLaws(){
		return evaluatedLehmanLaws;
	}
	public String getLawDecision(int lawId){
		return evaluatedLehmanLaws.get(lawId).getUserDecision();
	}
	
	public String getLawComment(int lawId){
		return evaluatedLehmanLaws.get(lawId).getUserComment();
	}
	
	public void addLehmanLaw(LehmanLaw evaluatedLaw){
		if( isLawAlreadyEvaluated(evaluatedLaw) )
			evaluatedLehmanLaws.remove(evaluatedLaw.getLawId());	
		evaluatedLehmanLaws.put(evaluatedLaw.getLawId(),evaluatedLaw);
	}
	
	public List<Version> getVersions(){
		return versions;
	}
	
	public void  addVersion(Version aVersion){
		versions.add(aVersion);
	}
	
	private double computeEffortEstimation(int version){
		double effortEstimation = 0;
		double computedSum = 0;
		for(int i = 0; i < version-1 ; i++)
			computedSum += 1 / (versions.get(i).getCurrentOperations()^2);
		if(computedSum == 0)
			computedSum = 1;
		
		effortEstimation = (versions.get(version).getCurrentOperations() 
								- initialNumberOfOperations) / computedSum ;
		return effortEstimation;
	}
	
	public void  computeMetricsForAllVersions(){
		versions.get(0).computeOperationMetrics(versions.get(0).getDate(),
						initialNumberOfOperations);
		versions.get(0).computeDataStructureMetrics(versions.get(0).getDate(),
						initialNumberOfDataStructures);
		versions.get(0).setEffortEstimation(computeEffortEstimation(0));		
		for(int i = 1; i < versions.size(); i++){
			versions.get(i).computeOperationMetrics(versions.get(i-1).getDate(),
							versions.get(i-1).getCurrentOperations());
			versions.get(i).computeDataStructureMetrics(
							versions.get(i-1).getDate(),
							versions.get(i-1).getCurrentDataStructures());
			versions.get(i).setEffortEstimation(computeEffortEstimation(i));
		}
	}
	
	public void setInitialNumberOfOperations(int numOfOperations){
		initialNumberOfOperations = numOfOperations;
	}
	
	public void setInitialNumberOfDataStructures(int numOfDataStructures){
		initialNumberOfDataStructures = numOfDataStructures;
	}
	
	public String getName(){
		return projectName;
	}
}
