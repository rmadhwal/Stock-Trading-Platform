package com.cs.trading.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Repositories.TraderRepository;

@RestController
public class UserController {
	
	@Autowired
	TraderRepository tr;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<Trader> returnAllUsers() {
		return tr.findAll();
	}
	
	@RequestMapping("/users/{id}")
	public User findUserById(@PathVariable(value="id") int id) {
		return tr.findUserById(id);
	}
}


