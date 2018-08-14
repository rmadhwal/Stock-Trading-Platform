package com.cs.trading.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;



@Repository
public class AdminRepository {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public int createTrader(Trader trader) {
			return jdbcTemplate.update("INSERT INTO users (firstname, lastname, password, phone, email, role) VALUES (?,?,?,?,?,?)",
					trader.getFirstName(),
					trader.getLastName(),
					trader.getPassword(),
					trader.getPhone(),
					trader.getEmail(),
					trader.getRole()
					);
		}
}
