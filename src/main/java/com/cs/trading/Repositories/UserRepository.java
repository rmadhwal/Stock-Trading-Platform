package com.cs.trading.Repositories;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("name"));
			return user;
		}
	}
}
