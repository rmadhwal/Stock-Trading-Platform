package com.cs.trading.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;



@Repository
public class AdminRepsitory {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public int createTrader(Trader trader) {
//			return 1;`
			return jdbcTemplate.update("INSERT INTO schema.users (firstname, lastname, password, phone, email, role) VALUES (?,?,?,?,?,?)",
					new Object[] {trader.getFirstName(),
					trader.getLastName(),
					trader.getPassword(),
					trader.getPhone(),
					trader.getEmail(),
					trader.getRole()}	
					);
		}
}
