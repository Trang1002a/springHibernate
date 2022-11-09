package demo.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "price")
    private float price;
    @Column(name = "sale_price")
    private float sale_price;
    @Column(name = "image")
    private String image;
    @Column(name = "created_date")
    private Date created_date;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category categories;

    @OneToMany(mappedBy = "products")
    private List<Warehouse> warehouses = new ArrayList<>();
    
	public Product() {
		super();
	}

	public Product(int id, String name, String status, float price, float sale_price, String image, Date created_date,
			Category categories, List<Warehouse> warehouses) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.price = price;
		this.sale_price = sale_price;
		this.image = image;
		this.created_date = created_date;
		this.categories = categories;
		this.warehouses = warehouses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSale_price() {
		return sale_price;
	}

	public void setSale_price(float sale_price) {
		this.sale_price = sale_price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Category getCategories() {
		return categories;
	}

	public void setCategories(Category categories) {
		this.categories = categories;
	}

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	


	
    
}
