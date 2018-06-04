package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.AutowiredBear;
import com.revature.beans.Bear;
import com.revature.beans.BearWithAutoByName;
import com.revature.beans.BearWithAutoByType;
import com.revature.beans.BearWithConstructor;
import com.revature.beans.BearWithSetter;
import com.revature.beans.Cave;

public class Driver {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		funWithCaves(ac);
		
		((AbstractApplicationContext) ac).close();
		
	}
	
	public static void funWithCaves(ApplicationContext ac) {
		Cave c = (Cave) ac.getBean("cave");
		System.out.println(c);
	}
	
	public static void funWithBears(ApplicationContext ac) {
		Bear b1 = (BearWithSetter) ac.getBean("bearWithSetter");
		b1.methodInBear();
		
		Bear b2 = (BearWithConstructor) ac.getBean("bearWithConstructor");
		b2.methodInBear();

		Bear b3 = (BearWithAutoByName) ac.getBean("autoBearByName");
		b3.methodInBear();

		Bear b4 = (BearWithAutoByType) ac.getBean("autoBearByType");
		b4.methodInBear();
		

		Bear b42 = (BearWithAutoByType) ac.getBean("autoBearByType");
		b42.methodInBear();
		
		Bear b5 = (AutowiredBear) ac.getBean("autowiredBear");
		b5.methodInBear();

		Bear b52 = (AutowiredBear) ac.getBean("autowiredBear");
		b52.methodInBear();
	}

}
