package com.cs.trading.Repositories;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Sector;
import com.cs.trading.Services.OrderService;
import com.cs.trading.Services.SectorService;

@Repository
public class CompanyRepository {
	

	@Autowired
	SectorService sectorService;
	@Autowired
	OrderService orderService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Company> findAll() {
		return jdbcTemplate.query("select * from companies", new CompanyRowMapper());
	}
	
	public Company findCompanyBySymbol(String symbol) {
		return jdbcTemplate.queryForObject("select * from companies where symbol=?", new CompanyRowMapper(), symbol);
	}
	
	public List<Company> findCompanyBySector(int sectorId){
		
		return jdbcTemplate.query("select * from companies where sectorid=?", new CompanyRowMapper(), sectorId);
	}
	
	public List<Company> findCompanyBySector(Sector sector){
		return findCompanyBySector(sector.getId());
	}
	
	public List<Company> findCompanyByLetters(String letters){
		return jdbcTemplate.query("select * from companies where LOWER(symbol) LIKE LOWER('"+letters+"%')", new CompanyRowMapper());
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
