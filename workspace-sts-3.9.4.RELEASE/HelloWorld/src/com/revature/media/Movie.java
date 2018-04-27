package com.revature.media;

import java.io.Serializable;

public class Movie extends Media implements Comparable<Movie>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String producer;
	
	public Movie(String producer, String title, int yearPublished, String genre) {
		super();
		this.producer = producer;
		this.title = title;
		this.yearPublished = yearPublished;
		this.genre = genre;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String author) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return "Movie [producer=" + producer + ", yearPublished=" + yearPublished + ", title=" + title + ", genre=" + genre
				+ "]";
	}

	public int compareTo(Movie m) {
		return this.getYearPublished().compareTo(getYearPublished());
	}

}
