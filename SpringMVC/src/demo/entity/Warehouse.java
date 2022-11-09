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
	@Column(name = "product_name")
	private String product_name;
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


	public Warehouse(String product_name, int quantity, int status) {
		super();
		this.product_name = product_name;
		this.quantity = quantity;
		this.status = status;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	
	
}
