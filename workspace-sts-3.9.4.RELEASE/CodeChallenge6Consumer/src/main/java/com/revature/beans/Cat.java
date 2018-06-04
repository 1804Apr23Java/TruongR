package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cat implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String catName;
	private String catPic;
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
