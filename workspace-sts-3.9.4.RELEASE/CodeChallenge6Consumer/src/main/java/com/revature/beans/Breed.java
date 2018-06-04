package com.revature.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Breed implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String breedName;

	public Breed(int id, String breedName) {
		super();
		this.id = id;
		this.breedName = breedName;
	}

	public Breed(String breedName) {
		super();
		this.breedName = breedName;
	}

	public Breed() {
		super();
	}

	@Override
	public String toString() {
		return "Breed [id=" + id + ", breedName=" + breedName + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	

}
