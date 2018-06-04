package com.revature.beans;

public class BearWithAutoByType extends Bear {

	
	//must have setter method (or constructor) with type of desired bean
	public void setSomethingOfTypeCave(Cave somethingElseWhatever) {
		this.cave = somethingElseWhatever;
	}

	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutoByType. this bear is: " + this.toString());
		System.out.println("cave: " + cave.toString());

	}

}
