package com.cs.trading.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Sector;
import com.cs.trading.Models.Trader;
import com.cs.trading.Models.User;
import com.cs.trading.Services.CompanyService;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.SectorService;



@Repository
public class AdminRepository {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		SectorService sectorService;
		@Autowired
		CompanyService companyService;
		@Autowired
		OrderService orderService;
		
		
		public List<User> listAllTraders(){
			return jdbcTemplate.query("select * from users", new UserRowMapper());
		}
		
		public int findLatestId() {
			return jdbcTemplate.queryForObject("select MAX(id) from users", Integer.class);
		}
		
		public List<User> getTrader(int id){
			return jdbcTemplate.query("SELECT * FROM USERS where id =" + id, new UserRowMapper());
		}
		
		public int deleteExistingTrader(int traderId) {
			//check if trader have orders in any status
			List<Order> orderList = orderService.findOrdersByTraderId(traderId); 
			if(orderList != null || orderList.size() > 0) {
			  Object[] params = { traderId };
			  int[] types = {Types.BIGINT};
			  //delete order then user because userId is the foreign key to user
			  jdbcTemplate.update("DELETE FROM orders WHERE ownerid = ?", params, types);
			  return jdbcTemplate.update("DELETE FROM users WHERE id = ?", params, types);
			}
			return 0; 
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
			
			List<Order> orders = orderService.findOrdersBySymbol(company.getSymbol());
			if(orders.isEmpty()) {
				return jdbcTemplate.update("DELETE FROM companies WHERE symbol=?",company.getSymbol());
			}else {
				return 0;
			}
		}
		
		public List<Sector> getAllMarketSectors() {
			
			return sectorService.findAll();
		}
		
		public int updateMarketSector(Sector sector) {
			
			int res = jdbcTemplate.update("UPDATE sectors SET name=?, description=? WHERE id=?",
								sector.getName(),
								sector.getDescription(),
								sector.getId()
								);
			return res;
		}
		
		public int deleteMarketSector(Sector sector) {
			
			List<Company> companies = companyService.findCompanyBySector(sector.getId());
			if(companies.isEmpty()) {
				 return jdbcTemplate.update("DELETE FROM sectors WHERE id=?",sector.getId());
			}else {
				return 0;
			}
		}
		
		class UserRowMapper implements RowMapper<User>
		{
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
				User user = new Trader();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getLong("phone"));
				user.setRole(Role.valueOf(rs.getString("role")));
				return user;
			}
		}
}
