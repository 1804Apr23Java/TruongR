package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CAT")
public class Cat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSequence")
	@SequenceGenerator(allocationSize = 1, name = "catSequence", sequenceName = "SQ_CAT_PK")
	@Column(name = "CAT_ID")
	private int id;
	
	@Column(name = "CAT_NAME")
	private String catName;
	
	@Column(name = "CAT_PIC")
	private String catPic;
	

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="BREED_ID")
	private Breed breed;

	public Cat(int id, String catName, String catPic, Breed breed) {
		super();
		this.id = id;
		this.catName = catName;
		this.catPic = catPic;
		this.breed = breed;
	}

	public Cat(String catName, String catPic, Breed breed) {
		super();
		this.catName = catName;
		this.catPic = catPic;
		this.breed = breed;
	}

	public Cat() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatPic() {
		return catPic;
	}

	public void setCatPic(String catPic) {
		this.catPic = catPic;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", catName=" + catName + ", catPic=" + catPic + ", breed=" + breed + "]";
	}

	
}
