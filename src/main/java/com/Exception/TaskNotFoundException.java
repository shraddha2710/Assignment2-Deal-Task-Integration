package com.Exception;

public class TaskNotFoundException extends Exception{
	
	public TaskNotFoundException(long id) {
	
		super(String.format("Deal  not found with id : '%s'", id));
	}
	
}
