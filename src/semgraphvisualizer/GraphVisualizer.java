package semgraphvisualizer;


import java.util.List;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import semdata.Version;

public class GraphVisualizer {
	private List<Version> versions;
	
	public GraphVisualizer(List<Version> versions){
		this.versions = versions;
	}
	
    public CategoryDataset createDatasetOperationChanges(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0; i < versions.size(); i++){
			dataset.addValue(
				versions.get(i).getNumberOfUpdatedDeletedAndAddedOperations(),
				"", Integer.toString(i+1));
		}
		return dataset;
	}
	
    public CategoryDataset createDatasetDataStructuresChanges(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0; i < versions.size(); i++){
			dataset.addValue(
			versions.get(i).getNumberOfUpdatedDeletedAndAddedDataStructures(),
			"", Integer.toString(i+1));
		}
		return dataset;
	}
    
    private int countVersionsPerYear(int year){
    	int counter = 0;
    	for(int i = 0 ; i < versions.size(); i++)
    		if( year == versions.get(i).getYearOfVersion())
    			counter++;
    	return counter;
    }
	
    public CategoryDataset createDatasetVersionsPerYear(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();   	
    	int minYear = versions.get(0).getYearOfVersion();
    	int maxYear = versions.get(versions.size()-1).getYearOfVersion();
    	for(int i = 0 ; i < versions.size(); i++){
    		dataset.addValue(countVersionsPerYear(minYear), "",
    				Integer.toString(minYear));
    		if(minYear == maxYear)
    			break;
    		minYear++;
    	}
		return dataset;
	}
	
    public CategoryDataset  createDatasetOperationsComplexity(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i = 0 ; i < versions.size(); i++){
    		dataset.addValue(versions.get(i).getOperationsComplexity(),"",
    				Integer.toString(i+1));
    	}
    	return dataset;
	}
	
    public CategoryDataset createDatasetMaintenanceActions(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		int totalChanges = 
    		versions.get(i).getNumberOfUpdatedDeletedAndAddedDataStructures() +
    		versions.get(i).getNumberOfUpdatedDeletedAndAddedOperations();
    		dataset.addValue(totalChanges,"",Integer.toString(i+1));
    	}
    	return dataset;
	}
	
    public CategoryDataset createDatasetOperationsGrowth(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getOperationsGrowth(), "", 
    			Integer.toString(i+1) );
    	}
    	return dataset;
	}
	
    public CategoryDataset createDatasetDataStructuresGrowth(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getDataStructuresGrowth(), "", 
    			Integer.toString(i+1) );
    	}
    	return dataset;
	}
	
    public CategoryDataset createDatasetOperationsWorkRate(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getOperationsWorkRate(), "", 
    			Integer.toString(i+1) );
    	}
    	return dataset;		
	}
	
    public CategoryDataset createDatasetDataStructuresWorkRate(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getDataStructuresWorkRate(), "", 
    			Integer.toString(i+1) );
    	}
    	return dataset;	
	}

    public CategoryDataset createDatasetOperationsNumber(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getCurrentOperations(),"",
    						Integer.toString(i+1));
    	}
    	return dataset;
	}
	
    public CategoryDataset createDatasetDataStructuresNumber(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getCurrentDataStructures(),"",
    						Integer.toString(i+1));
    	}
    	return dataset;
	}	
    
    private double computeEffortEstimationSum(int currentVersionId){
		double computedSum = 0;
		for(int j = 0; j < currentVersionId; j++)
			computedSum += versions.get(j).getEffortEstimation();    
		return computedSum;
    }
    
    private double computeNextVersionsOperationsEstimation(int currentVersionId){
    	double averageEffortEstimation = 0, nextVersionsOperationEstimation = 0;
		double currentOperations = 
				versions.get(currentVersionId).getCurrentOperations();
		averageEffortEstimation = computeEffortEstimationSum(currentVersionId) /
									(double)currentVersionId;
		nextVersionsOperationEstimation = currentOperations + 
				(averageEffortEstimation / Math.pow(currentOperations, 2.0) );
		return nextVersionsOperationEstimation;
    }
	
    public CategoryDataset createDatasetNumberOfOperationsEstimations(){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	double nextVersionsOperationEstimation = 0 ;
    	for(int i =0 ; i < versions.size(); i ++ ){
    		dataset.addValue(versions.get(i).getCurrentOperations(),
    						"Number Of Operations Actual",Integer.toString(i+1));
    		nextVersionsOperationEstimation = 
    								computeNextVersionsOperationsEstimation(i);
    		dataset.addValue(nextVersionsOperationEstimation,
    					"Number Of Operations Estimation",Integer.toString(i+2));   		
    	}
    	return dataset;
	}	
}
