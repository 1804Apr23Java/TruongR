package com.revature.producerconsumer;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

public class ProducerConsumerDriver {

	public static final int basketSize = 10;
	public static List<String> basket = new Vector<String>(basketSize);

	public static void main(String[] args) {

		final long startTime = System.nanoTime();

		//System.out.println("Starting at " + startTime);
		
		Consumer cons = new Consumer(10, startTime, 5000000000L);
		Producer prod = new Producer(10, startTime, 5000000000L);

		cons.start();
		prod.start();

		while (true) {
			if (!cons.isAlive() && !prod.isAlive()) {
				System.exit(0);
			}
		}

	}

}

class Consumer extends Thread {
	private int amount;
	private List<String> consumed;
	private long startTime;
	private long runTime;

	Consumer(int amount, long startTime, long runTime) {
		this.amount = amount;
		this.startTime = startTime;
		this.runTime = runTime;
	}

	public synchronized void run() {

		while (true) {
			try {
				consumed = new ArrayList<String>();
				if (ProducerConsumerDriver.basket.size() == 0)
					Thread.sleep(500);
				for (int i = 0; i < amount; i++) {
					if (ProducerConsumerDriver.basket.size() != 0)
						consumed.add(ProducerConsumerDriver.basket.remove(0));
				}
				System.out.println("Consumer has eaten: " + consumed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Consumer at time " + System.nanoTime());
			if ((System.nanoTime() - startTime) > runTime) {
				System.out.println("Consumer finished.");
				break;
			}
		}

	}
}

class Producer extends Thread {
	private int amount;
	private List<String> produce;
	private long startTime;
	private long runTime;

	Producer(int amount, long startTime, long runTime) {
		this.amount = amount;
		this.startTime = startTime;
		this.runTime = runTime;
	}

	public synchronized void run() {
		while (true) {
			try {
				produce = this.generateRandomStrings();
				int num = produce.size();
				while (!produce.isEmpty())
					if (ProducerConsumerDriver.basket.size() < ProducerConsumerDriver.basketSize)
						ProducerConsumerDriver.basket.add(produce.remove(0));
					else {
						Thread.sleep(500);
					}
				System.out.println("Producer has produced " + num + " objects.");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Producer at time " + System.nanoTime());
			if ((System.nanoTime() - startTime) > runTime) {
				System.out.println("Producer finished.");
				break;
			}
		}

	}

	public ArrayList<String> generateRandomStrings() {

		// just generates 1-amount strings from 0-99
		int len = (int) (Math.random() * amount) + 1;
		ArrayList<String> aList = new ArrayList<String>();

		for (int i = 0; i < len; i++)
			aList.add(String.valueOf((int) (Math.random() * 100)));

		return aList;
	}
}