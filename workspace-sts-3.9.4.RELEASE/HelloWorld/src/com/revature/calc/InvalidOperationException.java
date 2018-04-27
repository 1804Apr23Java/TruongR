package com.revature.calc;

public class InvalidOperationException extends Exception {
	
	public InvalidOperationException()
    {
       super("Error: must enter a valid operation.");
    }
}
