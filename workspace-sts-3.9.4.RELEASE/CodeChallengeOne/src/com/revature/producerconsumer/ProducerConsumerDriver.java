package com.revature.producerconsumer;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

public class ProducerConsumerDriver {

	public static final int basketSize = 50;
	public static List<String> basket = new Vector<String>(basketSize);

	public static void main(String[] args) {

		final long startTime = System.nanoTime();

		Consumer cons = new Consumer(10, startTime, 5000000);
		Producer prod = new Producer(10, startTime, 5000000);

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
	private int runTime;

	Consumer(int amount, long startTime, int runTime) {
		this.amount = amount;
		this.startTime = startTime;
		this.runTime = runTime;
	}

	public synchronized void run() {

		while (true) {
			try {
				consumed = new ArrayList<String>();
				for (int i = 0; i < amount; i++) {
					if (ProducerConsumerDriver.basket.size() != 0)
						consumed.add(ProducerConsumerDriver.basket.remove(0));
				}
				if (ProducerConsumerDriver.basket.size() == 0) {
					this.sleep(50000);
				}
				System.out.println("Consumer has eaten: " + consumed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (System.nanoTime() - startTime > runTime)
				break;
		}

	}
}

class Producer extends Thread {
	private int amount;
	private List<String> produce;
	private long startTime;
	private int runTime;

	Producer(int amount, long startTime, int runTime) {
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
						this.sleep(50000);
					}
				System.out.println("Producer has produced " + num + " objects.");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (System.nanoTime() - startTime > runTime)
				break;
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