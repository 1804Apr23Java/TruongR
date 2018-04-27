package com.revature.hello;

//classes should be pascal case
public class HelloWorld {

	// main method, camel case
	public static void main(String[] args) {
		System.out.println("hello world!");

		Orange o = new Orange();
		o.setColor("purple");
		System.out.println("Orange o is " + o.getColor());

		o.setVariety("Valencia");
		System.out.println("Orange o has variety " + o.getVariety());

		// what about Object class?

		Object obj = new Object();
		System.out.println(obj.toString()); // returns the fully
		// qualified classname + address in memory as a string representation

		System.out.println(o.toString());

		// equals and hascode

		Fruit f1 = new Fruit("red");
		Fruit f2 = new Fruit("green");
		Fruit f3 = new Fruit("green");
		System.out.println(f1.hashCode());
		System.out.println(f2.hashCode());
		System.out.println(f3.hashCode());
		System.out.println(f1.equals(f2));
		System.out.println(f2.equals(f3));
	}
}
