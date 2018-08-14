package com.cs.trading.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.User;
import com.cs.trading.Repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository ur;
	
//	@RequestMapping(value="/users", method=RequestMethod.GET)
//	public List<User> returnAllUsers() {
//		return ur.findAll();
//	}
//	
//	@RequestMapping("/users/{id}")
//	public User findUserById(@PathVariable(value="id") int id) {
//		return ur.findUserById(id);
//	}
}


