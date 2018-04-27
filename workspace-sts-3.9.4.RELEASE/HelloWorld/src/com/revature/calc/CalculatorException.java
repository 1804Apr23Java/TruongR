package com.revature.calc;

public class CalculatorException extends Exception {
	
	public CalculatorException()
    {
       super("Error: Must enter valid numeric input.");
    }
}
