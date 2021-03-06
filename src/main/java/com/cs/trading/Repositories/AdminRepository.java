package com.cs.trading.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Order;
import com.cs.trading.Models.Role;
import com.cs.trading.Models.Status;
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
			return jdbcTemplate.query("select * from users where role = \'TRADER\'", new UserRowMapper());
		}
		
		public int findLatestId() {
			return jdbcTemplate.queryForObject("select MAX(id) from users where role = \'TRADER\'", Integer.class);
		}
		
		public List<User> getTrader(int id){
			return jdbcTemplate.query("SELECT * FROM USERS where role = \'TRADER\' and id =" + id, new UserRowMapper());
		}
		
		public int deleteExistingTrader(int traderId) {
			//check if trader have orders in any status
			List<Order> orderList = orderService.findOrdersByTraderId(traderId); 
			if(orderList != null && orderList.size() > 0) {
				//do not delete trader if there is existing order
				return 2; 
			}
				return jdbcTemplate.update("DELETE FROM users WHERE role = \'TRADER\' and id = ?", traderId);
		}
		
		public Date retrieveLatestTimeTraderLastOrder(int traderId) {
			List<Order> orderList = orderService.findOrdersByTraderId(traderId);
			Collections.sort(orderList);
			return orderList.get(0).getTimeStamp(); 
		}
		
		public HashMap<Status, List<Order>> retrieveOrdersByStatus() {
			HashMap<Status, List<Order>> map = new HashMap<>();
			//three different status 
			map.put(Status.OPEN, orderService.findOrdersByStatus(Status.OPEN));
			map.put(Status.CANCELLED, orderService.findOrdersByStatus(Status.CANCELLED));
			map.put(Status.FULFILLED, orderService.findOrdersByStatus(Status.FULFILLED));
			return map; 
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
