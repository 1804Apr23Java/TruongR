package com.revature.threads;

public class MyThread extends Thread {

	
	@Override
	public void run() {
		System.out.println("Hello from " + this.getName());
		//this.setName("myAwesomeThread");
		//System.out.println("my name is now " + this.getName());
		
		for (int i = 0; i < 1000; i++) {
			Counter.setCount(i);
			System.out.println(this.getName() + "set count value to " + i);
		}
	}
}
