package pt.ua.querodoar;

import com.parse.ParseFile;

public class ClassNews {

	private String title;
	private ParseFile image;
	private String description;
	
	

	public ClassNews(String title, ParseFile image, String description) {
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

	public ParseFile getImage() {
		return image;
	}

	public void setImage(ParseFile image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
