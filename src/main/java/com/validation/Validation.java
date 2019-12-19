package com.validation;

public class Validation {
	
	
	public static void validateAmount(Double amount) throws Exception {
		if (amount < 0) {
			throw new Exception("Amount can not be negative");
		}
		}
	
	public static void ValidateRequiredField(String name) throws Exception{
		if(name != null && !name.isEmpty()) {
			System.out.println("true");
		}
		else 
		{
			throw new Exception("Enter Valid Name");
		}
		
		
	}
}
