package pt.ua.querodoar;

import com.parse.ParseFile;

public class ClassProduct {

	private String name;
	private ParseFile image;
	private String instID;
	private float price;
	private String description;
	
	
	public ClassProduct(String name, ParseFile image, String instID, float price,
			String description) {
		this.name = name;
		this.image = image;
		this.instID = instID;
		this.price = price;
		this.description = description;
	}
	
	public String getName() {
		return name;
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
	public String getInstID() {
		return instID;
	}
	public void setInstID(String instID) {
		this.instID = instID;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
