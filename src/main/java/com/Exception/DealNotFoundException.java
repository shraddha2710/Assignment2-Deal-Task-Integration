package com.Exception;

public class DealNotFoundException extends Exception{

	
	public DealNotFoundException(long id)
	{
		super(String.format("Deal  not found with id : '%s'", id));

	}
	
}
