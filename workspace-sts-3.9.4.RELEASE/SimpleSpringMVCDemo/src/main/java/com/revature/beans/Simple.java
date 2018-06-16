package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SIMPLE")
public class Simple {

	private int id;
	private String simpleName;
	private int simpleAge;

	public Simple(int id, String simpleName, int simpleAge) {
		super();
		this.id = id;
		this.simpleName = simpleName;
		this.simpleAge = simpleAge;
	}
	

	public Simple(String simpleName, int simpleAge) {
		super();
		this.simpleName = simpleName;
		this.simpleAge = simpleAge;
	}

	public Simple() {
		super();
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answerSequence")
	@SequenceGenerator(allocationSize = 1, name = "answerSequence", 
						sequenceName = "SQ_ANSWER_PK")
	@Column(name = "SIMPLEID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public int getSimpleAge() {
		return simpleAge;
	}

	public void setSimpleAge(int simpleAge) {
		this.simpleAge = simpleAge;
	}
	
	
}
