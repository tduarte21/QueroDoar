package pt.ua.querodoar;

import com.parse.ParseFile;

public class ClassInstitution {

	//private int id;
	private String objectID;
	private String name;
	private ParseFile image;
	private String location;
	private int year;
	private String description;

	public ClassInstitution(String objectID, String name, ParseFile image, String location, int year,String description) {
		super();
		//this.id = id;
		this.objectID = objectID;
		this.name = name;
		this.image = image;
		this.location = location;
		this.year = year;
		this.description = description;
	}

	/*public int getId(){
		return id;
	}*/
	
	
	
	public String getName() {
		return name;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ParseFile getImage() {
		return image;
	}

	public void setImage(ParseFile image) {
		this.image = image;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
