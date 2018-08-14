package com.cs.trading.Repositories;


import com.cs.trading.Models.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SectorRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Sector> findAll() {
		return jdbcTemplate.query("select * from sectors", new SectorRowMapper());
	}
	
	public Sector findSectorById(int id) {
		return jdbcTemplate.queryForObject("select * from sectors where id=?", new SectorRowMapper(), id);
	}
	
	class SectorRowMapper implements RowMapper<Sector>
	{
		@Override
		public Sector mapRow(ResultSet rs, int rowNum) throws SQLException{
			Sector sector = new Sector();
			sector.setId(rs.getInt("id"));
			sector.setName(rs.getString("name"));
			sector.setDescription(rs.getString("description"));
			return sector;
		}
	}
}
