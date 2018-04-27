package com.revature.comparison;

import java.util.Comparator;

import com.revature.media.Movie;

public class GenreComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie arg0, Movie arg1) {
		// TODO Auto-generated method stub
		return arg0.getGenre().compareTo(arg1.getGenre());
	}

}
