package com.cs.trading.Repositories;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cs.trading.Models.Company;
import com.cs.trading.Models.Sector;
import com.cs.trading.Services.CompanyService;

@Repository
public class SectorRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	CompanyService companyService;
	
	public List<Sector> findAll() {
		return jdbcTemplate.query("select * from sectors", new SectorRowMapper());
	}
	
	public Sector findSectorById(int id) {
		return jdbcTemplate.queryForObject("select * from sectors where id=?", new SectorRowMapper(), id);
	}
	

	
	public int updateMarketSector(Sector sector) {
		
		int res = jdbcTemplate.update("UPDATE sectors SET name=?, description=? WHERE id=?",
							sector.getName(),
							sector.getDescription(),
							sector.getId()
							);
		return res;
	}
	
	
	public int deleteMarketSector(int sectorId) {
		
		List<Company> companies = companyService.findCompanyBySector(sectorId);
		if(companies.isEmpty()) {
			 return jdbcTemplate.update("DELETE FROM sectors WHERE id=?",sectorId);
		}else {
			return 0;
		}
	}
	
	public int deleteMarketSector(Sector sector) {
		
		return deleteMarketSector(sector.getId());
	}
	
	public Sector getSectorById(int id) {
		try {
			return jdbcTemplate.queryForObject("select * FROM sectors WHERE id=?", new SectorRowMapper(),id);
		}catch(org.springframework.dao.EmptyResultDataAccessException ex) {
			return null;
		}
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
