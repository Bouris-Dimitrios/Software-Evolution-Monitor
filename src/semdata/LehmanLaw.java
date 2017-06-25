package semdata;

public class LehmanLaw {
	private int id;
	private String userComment;
	private String userDecision;
	private String lawName;
	
	public LehmanLaw(int id, String userDecision, String userComment,String lawName){
		this.id = id;
		this.userComment = userComment;
		this.userDecision = userDecision;
		this.lawName = lawName;
	}
	
	public String getLawName(){
		return lawName;
	}
	
	public int getLawId(){
		return id;
	}
	
	public String getUserDecision(){
		return userDecision;
	}
	
	public String getUserComment(){
		return userComment;
	}

}
