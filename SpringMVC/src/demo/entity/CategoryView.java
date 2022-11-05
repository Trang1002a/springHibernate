package demo.entity;

import javax.validation.constraints.NotEmpty;

public class CategoryView {
	@NotEmpty(message = "Vui long nhap ID")
	private String id;
	@NotEmpty(message = "Vui long nhap name")
	private String name;
	@NotEmpty(message = "Vui long chon file")
	private String image;
	
	public CategoryView() {
		super();
	}

	public CategoryView(@NotEmpty(message = "Vui long nhap ID") String id,
			@NotEmpty(message = "Vui long nhap name") String name,
			@NotEmpty(message = "Vui long chon file") String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}