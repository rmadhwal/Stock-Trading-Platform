package com.cs.trading.Repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cs.trading.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TraderRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int createTrader(Trader trader) {
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
	
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users", new UserRowMapper());
	}
	
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where id=?", new UserRowMapper(), id);
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
