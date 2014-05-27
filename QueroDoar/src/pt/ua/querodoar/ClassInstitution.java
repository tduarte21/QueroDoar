package pt.ua.querodoar;

public class ClassInstitution {

	//private int id;
	private String name;
	private int image;
	private String location;
	private int year;
	private String description;

	public ClassInstitution(String name,int image, String location, int year,
			String description) {
		super();
		//this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

	
	public int getImage() {
		return image;
	}

	public void setImage(int image) {
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
