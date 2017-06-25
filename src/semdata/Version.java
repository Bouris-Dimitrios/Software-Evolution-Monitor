package semdata;

import java.util.Date;

public class Version {
	private int id;
	private Date versionDate;
	private ChangesHolder operationChanges;
	private ChangesHolder dataStructureChanges;
	private Metrics operationMetrics;
	private Metrics dataStructureMetrics;
	private int currentOperations;
	private int currentDataStructures;
	private double effortEstimation;
	
	public Version(int id, Date versionDate , ChangesHolder operationChanges,
			ChangesHolder dataStructureChanges){
		this.id = id;
		this.versionDate = versionDate;	
		this.operationChanges = operationChanges;
		this.dataStructureChanges = dataStructureChanges;	
	}
	
	public int getNumberOfUpdatedDeletedAndAddedOperations(){
		return operationChanges.getAmountOfUpdatedDeletedAndAdded();
	}
	public double getOperationsComplexity(){
		return operationMetrics.getComplexity();
	}
	public int getNumberOfUpdatedDeletedAndAddedDataStructures(){
		return dataStructureChanges.getAmountOfUpdatedDeletedAndAdded();
	}
	public double getOperationsWorkRate(){
		return operationMetrics.getWorkRate();
	}
	public double getDataStructuresWorkRate(){
		return dataStructureMetrics.getWorkRate();
	}	
	public int getOperationsGrowth(){
		return operationChanges.getAmountOfAddedMinusDeleted();
	}
	public int getDataStructuresGrowth(){
		return dataStructureChanges.getAmountOfAddedMinusDeleted();
	}
	public int getYearOfVersion(){
		return versionDate.getYear() + 1900;
	}
	
	public Date getDate(){
		return versionDate;
	}
	
	public int getCurrentOperations(){
		return currentOperations;
	}
	
	public int getCurrentDataStructures(){
		return currentDataStructures;
	}	
	
	public void computeOperationMetrics(Date previousVersionDate,
										int currentNumberOfOperations){
		operationMetrics = new Metrics(currentNumberOfOperations,
							operationChanges,versionDate,previousVersionDate);
		currentOperations = currentNumberOfOperations + operationChanges.getAdded() - 
									operationChanges.getDeleted();
	}	
	
	public void setEffortEstimation(double effortEstimation){
		this.effortEstimation = effortEstimation;
	}
	
	public double getEffortEstimation(){
		return effortEstimation;
	}	
	
	public void computeDataStructureMetrics(Date previousVersionDate,
			int currentNumberOfDataStructures){
		dataStructureMetrics = new Metrics(currentNumberOfDataStructures,
				dataStructureChanges,versionDate,previousVersionDate);
		currentDataStructures = currentNumberOfDataStructures + 
								dataStructureChanges.getAdded() - 
								dataStructureChanges.getDeleted();

	}		
	
}
