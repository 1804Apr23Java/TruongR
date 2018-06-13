package com.revature.filter;

import com.netflix.zuul.ZuulFilter;

public class SimplePreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		System.out.println("hello from ZuulFilter. Imagine if this was doing authentication, or something..");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}