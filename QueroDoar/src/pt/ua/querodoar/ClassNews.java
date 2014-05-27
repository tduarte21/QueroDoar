package pt.ua.querodoar;

public class ClassNews {

	private String title;
	private int image;
	private String description;
	
	

	public ClassNews(String title, int image, String description) {
		super();
		this.title = title;
		this.image = image;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
