package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Simple;
import com.revature.repository.SimpleDao;

@Service
public class SimpleService {
	
	@Autowired
	SimpleDao simpleDao;
	
	public Simple getSimple(int id) {
		return simpleDao.findSimpleById(id);
	}
	
	public Simple addSimple(String simpleName, int simpleAge) {
		Simple simple = new Simple(simpleName, simpleAge);
		return simpleDao.save(simple);
	}
	
}
