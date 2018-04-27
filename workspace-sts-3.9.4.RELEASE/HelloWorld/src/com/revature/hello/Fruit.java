package com.revature.hello;

public class Fruit {

	public Fruit() {
		super();
	}

	public Fruit(String color) {
		super();
		this.color = color;
	}

	private String color;

	// void return type on a method returns nothing
	public void grow() {
		System.out.println("fruit is growing");
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) // compares actual memory address of object
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fruit other = (Fruit) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

}
