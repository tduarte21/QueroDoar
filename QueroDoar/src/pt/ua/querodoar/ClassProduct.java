package pt.ua.querodoar;

public class ClassProduct {

	private String name;
	private int image;
	private int instID;
	private float price;
	private String description;
	
	
	public ClassProduct(String name, int image, int instID, float price,
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
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public int getInstID() {
		return instID;
	}
	public void setInstID(int instID) {
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
