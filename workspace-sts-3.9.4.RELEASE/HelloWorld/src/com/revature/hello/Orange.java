package com.revature.hello;

public class Orange extends Fruit {
	private String variety;

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public Orange() {
		super(); // calling Fruit's noargs constructor
	}

	public Orange(String variety) {
		this(); // implicitly called even if left out
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "Orange [variety=" + variety + "]";
	}

	// overriding: providing a new implementation of an instance method in a
	// subclass
	// need to have same method signature as superclass's method

	// code blocks

	// instance
	{
		System.out.println("this is an instance code block and will run when Orange is instantiated");
	}

	static {
		System.out.println("this is an instance code block and will run when Orange passes through JVM");
	}

}
