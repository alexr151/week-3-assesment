package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bikes")
public class ListBike {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="MAKE")
	private String make;
	@Column(name="MODEL")
	private String model;
	
	
	public ListBike() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ListBike(String make, String model) {
		super();
		this.make = make;
		this.model = model;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}
	
	public String returnBikeDetails() {
		return make + ": " + model;
	}

}
