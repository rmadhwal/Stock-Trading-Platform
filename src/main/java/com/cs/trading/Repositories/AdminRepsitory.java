package com.cs.trading.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Models.Trader;
import com.cs.trading.Services.SectorService;



@Repository
public class AdminRepsitory {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		SectorService sectorService;
		
		public int createTrader(Trader trader) {
//			return 1;`
//			return jdbcTemplate.update("INSERT INTO users (firstname, lastname, password, phone, email, role) VALUES (?,?,?,?,?,?)",
//					new Object[] {trader.getFirstName(),
//					trader.getLastName(),
//					trader.getPassword(),
//					trader.getPhone(),
//					trader.getEmail(),
//					trader.getRole()}	
//					);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
	    	jdbcTemplate.update(
	    	    new PreparedStatementCreator() {
	    	    	String sql = "INSERT INTO users (firstname, lastname, password, phone, email, role) VALUES (?,?,?,?,?,?)";
	    	    	@Override
	    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	    	            PreparedStatement pst =
	    	                con.prepareStatement(sql, new String[] {"id"});
	    	            pst.setString(1, trader.getFirstName());
	    	            pst.setString(2, trader.getLastName());
	    	            pst.setString(3, trader.getPassword());
	    	            pst.setLong(4, trader.getPhone());
	    	            pst.setString(5, trader.getEmail());
	    	            pst.setString(6, trader.getRole().name());
	    	            return pst;
	    	        }
	    	    },
	    	    keyHolder);
	    	
	    	return keyHolder.getKey().intValue();
			
		}
		
		public int createCompany(Company company) {
			
			List<Sector> sectors = sectorService.findAll();
			
			for(Sector sector: sectors) {
				if(sector.getId()==company.getSector_id()){
					try {
						return jdbcTemplate.update("INSERT INTO companies (symbol, name, sectorid) VALUES (?,?,?)",
								company.getSymbol(),
								company.getName(),
								company.getSector_id()
								);
					}catch(DuplicateKeyException ex) {
						return -1;
					}
				}
			}
			return -2;
			
		}

		public int deleteCompany(Company company) {
			
			return 1;
		}
}
