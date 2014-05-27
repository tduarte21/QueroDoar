package pt.ua.querodoar;

public class ClassCity {
	
	private String objectID;
	private String name;
	private int points;
	
	
	
	public ClassCity(String objectID, String name, int points) {
		this.name = name;
		this.points = points;
		this.objectID=objectID;
	}
	
	
	public String getObjectID() {
		return objectID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
	

}
