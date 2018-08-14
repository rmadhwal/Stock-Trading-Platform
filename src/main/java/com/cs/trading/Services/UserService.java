package com.cs.trading.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.trading.Repositories.UserRepository;



@Component
public class UserService {

	@Autowired
	UserRepository uc;
	
	
	
}
