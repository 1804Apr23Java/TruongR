package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Cat;
import com.revature.repository.CatRepository;

@RestController
@RequestMapping("/cat")
public class CatController {
	
	@Autowired
	CatRepository cr;

	@GetMapping("/{id}")
	public ResponseEntity<Cat> getCat(@PathVariable int id) {
		Cat c = cr.findCatById(id);
		System.out.println(c);
		return new ResponseEntity<Cat>(c, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Cat>> getCats() {
		List<Cat> c =cr.findAll();
		System.out.println(c);
		return new ResponseEntity<List<Cat>>(c, HttpStatus.OK);
	}
	
}
