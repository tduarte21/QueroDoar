package pt.ua.querodoar;

import com.parse.ParseFile;

public class ClassCity {

	private String objectID;
	private String name;
	private int points;
	private ParseFile image;

	public ParseFile getImage() {
		return image;
	}

	public void setImage(ParseFile image) {
		this.image = image;
	}

	public ClassCity(String objectID, String name, int points, ParseFile image) {
		this.name = name;
		this.points = points;
		this.objectID = objectID;
		this.image = image;
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
