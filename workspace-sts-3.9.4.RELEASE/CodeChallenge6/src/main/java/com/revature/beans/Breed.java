package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BREED")
public class Breed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSequence")
	@SequenceGenerator(allocationSize = 1, name = "catSequence", sequenceName = "SQ_CAT_PK")
	@Column(name = "BREED_ID")
	private int id;

	@Column(name = "BREED_NAME")
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
