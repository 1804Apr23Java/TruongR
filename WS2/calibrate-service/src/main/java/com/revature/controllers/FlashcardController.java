package com.revature.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.beans.Category;
import com.revature.beans.Flashcard;

@RestController
public class FlashcardController {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="getFakeFlashcards")
	@GetMapping("/getFlashcards")
	public ResponseEntity<? extends ArrayList<Flashcard>> getFlashcards() {
		ResponseEntity<? extends ArrayList<Flashcard>> response = this.restTemplate.getForEntity(
				"http://localhost:9090/api/cards/api/allFlashcards",
				(Class<? extends ArrayList<Flashcard>>) ArrayList.class);
		return response;
	}

	public ResponseEntity<? extends ArrayList<Flashcard>> getFakeFlashcards() {
		ArrayList<Flashcard> dummyList = new ArrayList<>();
		Flashcard f = new Flashcard(3, "Where do you stand if you're cold?", "In the corner, it's 90 degrees.",
				new Category(2, "meteorology"));
		Flashcard f2 = new Flashcard(75, "How much wood could a woodchuck chuck?", "Can a woodchuck chuck wood?",
				new Category(2, "meteorology"));
		dummyList.add(f);
		dummyList.add(f2);
		return new ResponseEntity(dummyList, HttpStatus.OK);
	}

}
