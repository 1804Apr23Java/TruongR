package com.revature.media;

public  abstract class Media {
	protected Integer yearPublished;
	protected String title;
	protected String genre;
	

	public Integer getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Media [yearPublished=" + yearPublished + ", title=" + title + ", genre=" + genre + "]";
	}
//	public int compareTo (Media m) {
//		return this.yearPublished.compareTo(m.getYearPublished());
//	}

	

}