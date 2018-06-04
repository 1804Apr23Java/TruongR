package com.revature.service;

public abstract class AnimalService {

	public abstract int getSpeed();
	public abstract Boolean isFull();
	public abstract void setFull(Boolean full);
	public abstract void animalChasesYou();
	public void animalCatchesYou() {
		System.out.println("Hope it's not hungry.");
	}
}
