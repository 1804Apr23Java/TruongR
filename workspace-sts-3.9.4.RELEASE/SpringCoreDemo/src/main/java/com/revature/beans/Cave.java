package com.revature.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cave implements InitializingBean, DisposableBean {
	
	private int id;
	private String name;
	
	public Cave(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Cave() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cave [id=" + id + ", name=" + name + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("after properties set");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("disposable bean destroy");
		
	}
	
	public void defaultInit() {
		System.out.println("default init method");
	}
	
	public void defaultDestroy() {
		System.out.println("default destroy");
	}
}
