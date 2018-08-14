package com.cs.trading.Repositories;


import com.cs.trading.Models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CompanyRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Company> findAll() {
		return jdbcTemplate.query("select * from companies", new CompanyRowMapper());
	}
	
	public Company findCompanyBySymbol(String symbol) {
		return jdbcTemplate.queryForObject("select * from companies where symbol=?", new CompanyRowMapper(), symbol);
	}
	
	class CompanyRowMapper implements RowMapper<Company>
	{
		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException{
			Company company = new Company();
			company.setSymbol(rs.getString("symbol"));
			company.setName(rs.getString("name"));
			company.setSector_id(rs.getInt("sectorid"));
			return company;
		}
	}
}
