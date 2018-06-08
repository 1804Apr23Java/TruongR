package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.model.FoodComment;

@WebService
public interface Page {

	
	public List <FoodComment> getAllFoodComments();
	
	public String addComment(FoodComment fc);
}
