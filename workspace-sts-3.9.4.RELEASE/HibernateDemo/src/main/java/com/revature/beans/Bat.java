package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("bat")
public class Bat extends Animal implements Serializable {
	
	public Bat() {
		super();
	}

	public Bat(String name, Cave cave, int wingspan) {
		super(name, cave);
		this.wingspan = wingspan;
	}

	@Column(name="BAT_WINGSPAN")
	private int wingspan;

	public int getWingspan() {
		return wingspan;
	}

	public void setWingspan(int wingspan) {
		this.wingspan = wingspan;
	}

	@Override
	public String toString() {
		return "Bat [wingspan=" + wingspan + "]";
	}

}
