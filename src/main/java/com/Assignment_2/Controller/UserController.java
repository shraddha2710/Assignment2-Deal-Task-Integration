package com.Assignment_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment_2.Model.User;
import com.Assignment_2.Repository.UserRepository;

@RestController
@RequestMapping(path ="user")
public class UserController {
	
	@GetMapping("/")
	public String test()
	{
		return "test user";
	}
	
	@Autowired
	UserRepository userrepository;
	
	@PostMapping("user-post")
	public User create(@RequestBody User user) {
		return userrepository.save(user);
	}
	
}
