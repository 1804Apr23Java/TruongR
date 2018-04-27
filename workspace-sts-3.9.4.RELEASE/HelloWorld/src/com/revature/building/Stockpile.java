package com.revature.building;

import java.util.ArrayList;

public class Stockpile {
	private int numWood;
	private int numSteel;
	private int numDirt;
	private double money;
	private double numLoans;
	
	public static final int PRICE_WOOD = 10;
	public static final int PRICE_STEEL = 50;
	public static final int PRICE_DIRT = 5;
	
	
	public Stockpile(int numWoodPlanks, int numSteelBars, int numLbDirt, double money) {
		super();
		this.numWood = numWoodPlanks;
		this.numSteel = numSteelBars;
		this.numDirt = numLbDirt;
		this.money = money;
	}
	
	public Stockpile() {
		super();
		this.numWood = 0;
		this.numSteel = 0;
		this.numDirt = 0;
		this.money = 0;
	}
	
	public void buyWood(int numWoodAdded) {
		this.numWood += numWoodAdded;
		this.money -= numWoodAdded*PRICE_WOOD;
		System.out.println("Bought " + numWoodAdded + " wood.");
		while (this.money < 0) {
			System.out.println("Ran out of cash, taking out a loan.");
			takeOutLoan();
		}
			
	}
	
	public void buySteel(int numSteelAdded) {
		this.numSteel += numSteelAdded;
		this.money -= numSteelAdded*PRICE_WOOD;
		System.out.println("Bought " + numSteelAdded + " steel.");
		while (this.money < 0) {
			System.out.println("Ran out of cash, taking out a loan.");
			takeOutLoan();
		}
	}
	
	public void buyDirt(int numDirtAdded) {
		this.numDirt += numDirtAdded;
		this.money -= numDirtAdded*PRICE_WOOD;
		System.out.println("Bought " + numDirtAdded + "dirt.");
		while (this.money < 0) {
			System.out.println("Ran out of cash, taking out a loan.");
			takeOutLoan();
		}
	}
	
	public void takeOutLoan() {
		this.money += 1000;
		this.numLoans += 1;
	}
}
