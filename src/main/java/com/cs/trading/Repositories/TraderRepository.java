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
		return jdbcTemplate.query("select * from users where role = \'TRADER\'", new UserRowMapper());
	}
	
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("select * from users where role = \'TRADER\' and id=?", new UserRowMapper(), id);
	}
	
	public List<Trader> findTopNTradersByNumber(int limit){
		return jdbcTemplate.query("select * " + 
				"from users " + 
				"inner join (select ownerid, count(id) as numTrade from orders group by ownerid order by numTrade desc limit ?) as order_count " + 
				"on order_count.ownerid = users.id", new TraderByNumTradeRowMapper(), limit);
	}
	
	public List<Trader> findTopNTradersByVolume(int limit){
//		return jdbcTemplate.query("select * " + 
//				"from users " + 
//				"inner join (select ownerid, count(id) as numTrade from orders group by ownerid order by numTrade desc limit ?) as order_count " + 
//				"on order_count.ownerid = users.id", new TraderByVolumeRowMapper(), limit);
		return null; 
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
	
	class TraderByNumTradeRowMapper implements RowMapper<Trader>
	{
		@Override
		public Trader mapRow(ResultSet rs, int rowNum) throws SQLException{
			Trader user = new Trader();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getLong("phone"));
			user.setRole(Role.valueOf(rs.getString("role")));
			user.setNumTrades(rs.getInt("numtrade"));
			return user;
		}
	}
	
	class TraderByVolumeRowMapper implements RowMapper<User>
	{
		@Override
		public Trader mapRow(ResultSet rs, int rowNum) throws SQLException{
			Trader user = new Trader();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getLong("phone"));
			user.setRole(Role.valueOf(rs.getString("role")));
			user.setVolume(Integer.getInteger(rs.getString("volume")));
			return user;
		}
	}
}
