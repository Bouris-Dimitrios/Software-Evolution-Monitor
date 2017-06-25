package semdata;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Metrics {
	private int amountInCurrentVersion;
	private double evolutionRate;
	private double complexity;
	private double workRate;
	
	
	private  long computeDistanceBetweenTwoDates(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date1.getTime() - date2.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	private void computeEvolutionRate(ChangesHolder changes){
		int amountInPreviousVersion = amountInCurrentVersion
				+ ( changes.getDeleted() - 	changes.getAdded());
		evolutionRate = amountInCurrentVersion - amountInPreviousVersion;		
	}
	private void computeComplexity(ChangesHolder changes){
		if(changes.getAdded() != 0)
			complexity = changes.getAmountOfUpdatedAndDeleted() /
													changes.getAdded();
		else
			complexity = changes.getAmountOfUpdatedAndDeleted();
	}
	private void computeWorkRate(ChangesHolder changes, long distanceInDays){
		if(distanceInDays == 0) distanceInDays = 1;
		workRate = (double)changes.getAmountOfUpdatedDeletedAndAdded() /
					(double)distanceInDays;
	}
	public Metrics(){
		amountInCurrentVersion = 0;
		evolutionRate = complexity = workRate = 0;
	}

	public Metrics(int amount, ChangesHolder changes,
		Date dateOfCurrentVersion,Date dateOfPreviousVersion){
		this.amountInCurrentVersion = amount;
		long distanseInDays = computeDistanceBetweenTwoDates(dateOfCurrentVersion,
				dateOfPreviousVersion, TimeUnit.DAYS);
		computeEvolutionRate(changes);
		computeComplexity(changes);
		computeWorkRate(changes,distanseInDays);
	}
	public double getWorkRate(){
		return workRate;
	}
	public double getComplexity(){
		return complexity;
	}	
}
