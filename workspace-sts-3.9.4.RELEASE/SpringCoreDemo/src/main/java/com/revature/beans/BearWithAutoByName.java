package com.revature.beans;

public class BearWithAutoByName extends Bear {
	
	public void setCave(Cave cave) {
		this.cave = cave;
	}

	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutoByName. this bear is: " + this.toString());
		System.out.println("cave: " + cave.toString());
	}

}
