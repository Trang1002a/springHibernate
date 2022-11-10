package demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_warehouse")
public class Warehouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "status")
    private int status;
	
	@ManyToOne()
    @JoinColumn(name = "product_id")
    private Product products;
	
	public Warehouse() {
		super();
	}



	public Warehouse(int id, int quantity, int status, Product products) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.status = status;
		this.products = products;
	}



	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Product getProducts() {
		return products;
	}


	public void setProducts(Product products) {
		this.products = products;
	}
	
	
}
