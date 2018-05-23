package com.revature.util;

public enum EmployeeField {
	USERNAME ("USERNAME"),
	PASSWORD ("PASSWORD"),
	FIRSTNAME ("FIRSTNAME"),
	LASTNAME ("LASTNAME"),
	ADDRESS ("ADDRESS"),
	CITY ("CITY"),
	STATE ("STATE"),
	ZIP ("ZIP"),
	PHONE ("PHONE"),
	EMAIL ("EMAIL")
	;
	
	private final String fieldName;
	
	private EmployeeField (String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return this.fieldName;
	}
	
}
