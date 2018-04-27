package com.revature.comparison;

import java.util.Comparator;

import com.revature.media.Movie;

public class YearComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		// TODO Auto-generated method stub
		return o1.getYearPublished().compareTo(o2.getYearPublished());
	}

}
