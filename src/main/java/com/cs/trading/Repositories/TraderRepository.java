package com.cs.trading.Repositories;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cs.trading.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TraderRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users", new UserRowMapper());
	}
	
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?", new UserRowMapper(), id);
	}

	public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, double price, int quantity, String tickerSymbol, int traderId){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String timestampString = formatter.format(timestamp);
		String orderTypeString = orderType.name();
		String statusString = status.name();
		String sideString = side.name();
		return jdbcTemplate.update("insert into orders( ordertype, status, side, timestamp, filledquantity, price, quantity, tickersymbol, ownerid) VALUES (?,?,?,?,?,?,?,?,?)",orderTypeString, statusString, sideString, timestampString, 0, price, quantity, tickerSymbol, traderId);
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
//			user.setRole(Role.toSetring(rs.getString("role"));
			return user;
		}
	}
}
