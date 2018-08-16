package com.cs.trading.Repositories;


import com.cs.trading.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Transaction> findAll() {
		return jdbcTemplate.query("select * from transactions", new TransactionRowMapper());
	}

	
	public Transaction findTransactionById(int id) {
		return jdbcTemplate.queryForObject("select * from transactions where id=?", new TransactionRowMapper(), id);
	}


	public int addTransaction(int buyOrderId, int sellOrderId, int quantity, double price, Date timestamp){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		String timestampString = formatter.format(timestamp);
		return jdbcTemplate.update("insert into transactions(buyorderid, sellorderid, quantity, price, timestamp) VALUES (?,?,?,?,?)",buyOrderId, sellOrderId, quantity, price, timestampString);
	}

	class TransactionRowMapper implements RowMapper<Transaction>
	{
		@Override
		public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException{
			Transaction transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setBuyOrderId(rs.getInt("buyorderid"));
			transaction.setSellOrderId(rs.getInt("sellorderid"));
			transaction.setQuantityTraded(rs.getInt("quantity"));
			transaction.setPrice(rs.getDouble("price"));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
			try {
				Date date = formatter.parse(rs.getString("timestamp"));
				transaction.setTimeStamp(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return transaction;
		}
	}
}
