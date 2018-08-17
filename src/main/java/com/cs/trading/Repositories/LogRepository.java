package com.cs.trading.Repositories;


import com.cs.trading.Models.EventType;
import com.cs.trading.Models.Log;
import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Side;
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
public class LogRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static public Integer getInteger(ResultSet rs, String strColName) throws SQLException {
		int nValue = rs.getInt(strColName);
		return rs.wasNull() ? null : nValue;
	}
	
	public List<Log> findAll() {
		return jdbcTemplate.query("select * from logs", new LogRowMapper());
	}

	public Log findLogById(int id) {
		return jdbcTemplate.queryForObject("select * from logs where id=?", new LogRowMapper(), id);
	}

	public int findLatestId() {
		return jdbcTemplate.queryForObject("select MAX(id) from logs", Integer.class);
	}


	public int addLog(EventType eventType, Integer buySideOrderOwnerId, Integer sellSideOrderOwnerId, Integer quantity, Double price, OrderType buyOrderType, OrderType sellOrderType, String tickerSymbol, Date timestamp){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		String timestampString = formatter.format(timestamp);
		String buyOrderTypeString;
		String sellOrderTypeString;
		if(buyOrderType == null){
			buyOrderTypeString = null;
			sellOrderTypeString = null;
		}
		else {
			buyOrderTypeString = buyOrderType.name();
			sellOrderTypeString = sellOrderType.name();
		}
		return jdbcTemplate.update("insert into logs(eventType, buysideorderownerid, sellsideorderownerid, quantity, price, buyordertype, sellordertype, tickerSymbol, timestamp) VALUES (?,?,?,?,?,?,?,?,?)", eventType.name(), buySideOrderOwnerId, sellSideOrderOwnerId, quantity, price, buyOrderTypeString, sellOrderTypeString, tickerSymbol, timestampString);
	}

	class LogRowMapper implements RowMapper<Log>
	{
		@Override
		public Log mapRow(ResultSet rs, int rowNum) throws SQLException{
			Log log = new Log();
			log.setId(rs.getInt("id"));
			log.setEventType(EventType.valueOf(rs.getString("eventType")));
			log.setBuySideOrderOwnerId(getInteger(rs,"buysideorderownerid"));
			log.setSellSideOrderOwnerId(getInteger(rs,"sellsideorderownerid"));
			log.setQuantity(rs.getInt("quantity"));
			log.setPrice(rs.getDouble("price"));
			if(rs.getString("buyordertype") != null)
				log.setBuyOrderType(OrderType.valueOf(rs.getString("buyordertype")));
			else
				log.setBuyOrderType(null);
			if(rs.getString("sellordertype") != null)
				log.setSellOrderType(OrderType.valueOf(rs.getString("sellordertype")));
			else
				log.setSellOrderType(null);
			log.setTickerSymbol(rs.getString("tickersymbol"));
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
			try {
				Date date = formatter.parse(rs.getString("timestamp"));
				log.setTimeStamp(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return log;
		}
	}
}
