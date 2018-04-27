package com.revature.oop;

public abstract class Animal {
	
	public static String latinName = "Animalis"; 
	private String name;
	
	public Animal() {
		super();
	}
	
	public Animal(String name) {
		this();
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Animal [name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	abstract String makeNoise();
	
}
