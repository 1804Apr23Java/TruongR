package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Simple;
import com.revature.service.SimpleService;

@CrossOrigin
@RestController
@RequestMapping("/simple")
public class SimpleController {
	
	@Autowired
	SimpleService simpleService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Simple> getSimpleById(@PathVariable int id) {
		return new ResponseEntity<Simple>(simpleService.getSimple(id), HttpStatus.OK);
	}
}
