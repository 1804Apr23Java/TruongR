package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.model.FoodComment;

@WebService(endpointInterface="com.revature.service.Page")
public class PageImpl implements Page {

	@Override
	public List<FoodComment> getAllFoodComments() {
		List<FoodComment> fcList = new ArrayList<>();
		fcList.add(new FoodComment(1, "David Brewer", "This food tastes of soap?"));
		fcList.add(new FoodComment(2, "JJ Mubang", "i swear i like five guys"));
		fcList.add(new FoodComment(3, "Kevin Magno", "honestly i'm pretty impressed with dairy queen"));
		return fcList;
	}

	@Override
	public String addComment(FoodComment fc) {
		return "successfully added" + fc.getComment();
	}

	
	
}
