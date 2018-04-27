package com.revature.calc;

import java.util.Scanner;
import java.lang.reflect.*;

//takes in two numbers, performs selected operations (+/-/*/div), use Scanner, String input
//asks for desired input type (Number), need to convert string -> Integer, Double, etc., display result

public class Calculator {

	public static void main(String[] args) {
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		Number output = 0;

		while (running) {

			// code in first input number
			System.out.print("Enter first number: ");
			String firstNumS = scan.next();
			// code in second input number
			System.out.print("Enter second number: ");
			String secondNumS = scan.next();

			try {
				if (!firstNumS.matches("[-+]?\\d*\\.?\\d+") || !secondNumS.matches("[-+]?\\d*\\.?\\d+")) {
					throw new CalculatorException();
				}
			} catch (CalculatorException e) {
				System.out.println(e.getMessage());
				continue;
			}

			// code in second input number
			System.out.print("Enter operation: ");
			String operation = scan.next();

			System.out.print("Enter numeric type (one of Short, Integer, Long, Float, or Double): ");
			String numType = scan.next();
			try {
				switch (numType.toLowerCase()) {
				case "short":
					output = shortCalc(new Short(firstNumS), new Short(secondNumS), operation);
					break;
				case "integer": case "int":
					output = intCalc(new Integer(firstNumS), new Integer(secondNumS), operation);
					break;
				case "long":
					output = longCalc(new Long(firstNumS), new Long(secondNumS), operation);
					break;
				case "float":
					output = floatCalc(new Float(firstNumS), new Float(secondNumS), operation);
					break;
				case "double":
					output = doubleCalc(new Double(firstNumS), new Double(secondNumS), operation);
					break;
				default:
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: No decimal in integer types (short, integer, long).");
				continue;
			} catch (InvalidOperationException e) {
				System.out.println(e.getMessage());
				continue;
			} catch (ArithmeticException e) {
				System.out.println("Error: No division by 0.");
				continue;
			}

			System.out.println("Your result is: " + output +".");
			System.out.print("Continue? (y/n) ");

			if (scan.next().toLowerCase().equals("n")) {
				break;
			} else {
				continue;
			}

		}
		
		scan.close();
		System.out.println("bye");

	}

	public static Short shortCalc(Short n1, Short n2, String operation) throws InvalidOperationException {
		switch (operation.toLowerCase()) {
		case "+": case "add": case "addition":
			return (short) (n1 + n2);
		case "-": case "minus": case "subtract": case "sub": case "subtraction":
			return (short) (n1 - n2);
		case "*": case "times": case "multiply": case "multiplication":
			return (short) (n1 * n2);
		case "/": case "divide": case "division": case "div":
			if (n2 == 0) {
				throw new ArithmeticException();
			}
			return (short) (n1 / n2);
		default:
			throw new InvalidOperationException();
		}
	}

	public static Integer intCalc(Integer n1, Integer n2, String operation) throws InvalidOperationException {
		switch (operation.toLowerCase()) {
		case "+": case "add": case "addition":
			return (int) (n1 + n2);
		case "-": case "minus": case "subtract": case "sub": case "subtraction":
			return (int) (n1 - n2);
		case "*": case "times": case "multiply": case "multiplication":
			return (int) (n1 * n2);
		case "/": case "divide": case "division": case "div":
			if (n2 == 0) {
				throw new ArithmeticException();
			}
			return (int) (n1 / n2);
		default:
			throw new InvalidOperationException();
		}
	}

	public static Long longCalc(Long n1, Long n2, String operation) throws InvalidOperationException {
		switch (operation.toLowerCase()) {
		case "+": case "add": case "addition":
			return (long) (n1 + n2);
		case "-": case "minus": case "subtract": case "sub": case "subtraction":
			return (long) (n1 - n2);
		case "*": case "times": case "multiply": case "multiplication":
			return (long) (n1 * n2);
		case "/": case "divide": case "division": case "div":
			if (n2 == 0) {
				throw new ArithmeticException();
			}
			return (long) (n1 / n2);
		default:
			throw new InvalidOperationException();
		}
	}

	public static Double doubleCalc(Double n1, Double n2, String operation) throws InvalidOperationException {
		switch (operation.toLowerCase()) {
		case "+": case "add": case "addition":
			return (double) (n1 + n2);
		case "-": case "minus": case "subtract": case "sub": case "subtraction":
			return (double) (n1 - n2);
		case "*": case "times": case "multiply": case "multiplication":
			return (double) (n1 * n2);
		case "/": case "divide": case "division": case "div":
			if (n2 == 0) {
				throw new ArithmeticException();
			}
			return (double) (n1 / n2);
		default:
			throw new InvalidOperationException();
		}
	}

	public static Float floatCalc(Float n1, Float n2, String operation) throws InvalidOperationException {
		switch (operation.toLowerCase()) {
		case "+": case "add": case "addition":
			return (float) (n1 + n2);
		case "-": case "minus": case "subtract": case "sub": case "subtraction":
			return (float) (n1 - n2);
		case "*": case "times": case "multiply": case "multiplication":
			return (float) (n1 * n2);
		case "/": case "divide": case "division": case "div":
			if (n2 == 0) {
				throw new ArithmeticException();
			}
			return (float) (n1 / n2);
		default:
			throw new InvalidOperationException();
		}
	}
}
