package semdata;

public class ChangesHolder {
	private int added;
	private int deleted;
	private int updated;

	public ChangesHolder(int numberOfAdded,int  numberOfDeleted,
						int numberOfUpdated){
		added = numberOfAdded;
		deleted = numberOfDeleted;
		updated = numberOfUpdated;
	}
	public int getAmountOfUpdatedAndDeleted(){
		return updated + deleted;
	}
	public int getAmountOfAddedMinusDeleted(){
		return added - deleted;
	}
	public int getAdded(){
		return added;
	}
	
	public int getDeleted(){
		return deleted;
	}
	public int getAmountOfUpdatedDeletedAndAdded(){
		return updated + deleted + added;
	}
}
