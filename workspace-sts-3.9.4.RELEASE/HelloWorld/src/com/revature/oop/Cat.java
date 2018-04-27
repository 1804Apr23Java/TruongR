package com.revature.oop;

public class Cat extends Animal implements Domestic {

	@Override
	String makeNoise() {
		// TODO Auto-generated method stub
		return "meow";
	}
	
	public void pet() {
		System.out.println("petting cat");
	}
	
}
