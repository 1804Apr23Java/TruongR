package com.revature.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.service.PersonService;
import com.revature.service.TigerService;

public class WalkInTheWoods {

	public static void main(String[] args) {

		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		TigerService ts = (TigerService) ac.getBean("tigerService");
		PersonService ps = (PersonService) ac.getBean("personService");
		
		ps.wakeUpAnimal();
		ts.animalChasesYou();
		ts.animalCatchesYou();
		
		ac.close();
	}

}
