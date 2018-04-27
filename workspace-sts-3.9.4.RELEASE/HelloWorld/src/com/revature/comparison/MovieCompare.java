package com.revature.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.media.Book;
import com.revature.media.Media;
import com.revature.media.Movie;

public class MovieCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Movie> mediaList = new ArrayList<Movie>();
		Movie m1 = new Movie("Steven Spielberg", "Jaws", 1975, "horror");
		Movie m2 = new Movie("David Michael Latt", "Sharknado", 2013, "science fiction");
		Movie m3 = new Movie("Quentin Tarantino", "Pulp Fiction", 1994, "tarantino");
		mediaList.add(m2);
		mediaList.add(m1);
		mediaList.add(m3);
		
		for (Movie m: mediaList) {
			System.out.println(m);
		}
		
		//sortWithComparable(mediaList);
		sortWithYearComparator(mediaList);
		sortWithGenreComparator(mediaList);
	}

	public static List<Movie> sortWithComparable (List<Movie> movieList) {
		Collections.sort(movieList);
		System.out.println("Movies after sort:");
		for (Movie m: movieList) {
			System.out.println(m);
		}
		return movieList;
	}
	
	public static List<Movie> sortWithYearComparator (List<Movie> movieList) {
		Collections.sort(movieList, new YearComparator());
		System.out.println("Movies after sort:");
		for (Movie m: movieList) {
			System.out.println(m);
		}
		return movieList;
	}
	
	public static List<Movie> sortWithGenreComparator (List<Movie> movieList) {
		Collections.sort(movieList, new GenreComparator());
		System.out.println("Movies after sort:");
		for (Movie m: movieList) {
			System.out.println(m);
		}
		return movieList;
	}
}
