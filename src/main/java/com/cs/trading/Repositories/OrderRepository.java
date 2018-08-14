package com.cs.trading.Repositories;


import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Status;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Side;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Order> findAll() {
		return jdbcTemplate.query("select * from orders", new OrderRowMapper());
	}
	
	public Order findOrderById(int id) {
		return jdbcTemplate.queryForObject("select * from orders where id=?", new OrderRowMapper(), id);
	}

	public List<Order> findOrderByTraderId(int traderId) {
		return jdbcTemplate.query("select * from orders where ownerid=?", new OrderRowMapper(), traderId);
	}
	
	class OrderRowMapper implements RowMapper<Order>
	{
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setOrderType(OrderType.valueOf(rs.getString("ordertype")));
			order.setStatus(Status.valueOf(rs.getString("status")));
			order.setSide(Side.valueOf(rs.getString("side")));

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				Date date = formatter.parse(rs.getString("timestamp"));
				order.setTimeStamp(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			order.setFilledQuantity(rs.getInt("filledquantity"));
			order.setPrice(rs.getDouble("price"));
			order.setQuantity(rs.getInt("quantity"));
			order.setTickerSymbol(rs.getString("tickersymbol"));
			order.setOwnerId(rs.getInt("ownerid"));
			return order;

		}
	}
}
