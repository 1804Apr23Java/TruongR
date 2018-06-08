package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Cat;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{id}")
	public ResponseEntity<String> printString(@PathVariable int id) {
		Cat cat = restTemplate.getForObject("https://codechallenge6.cfapps.io/cat/" + id, Cat.class);
		String str = "<!DOCTYPE html> <head><title>Cat!</title></head> <body><p>This cat is named " + cat.getCatName()
				+ " who is a " + cat.getBreed().getBreedName() + ". <br><img src=\"" + cat.getCatPic()
				+ "\"/></p></body>";
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

}
