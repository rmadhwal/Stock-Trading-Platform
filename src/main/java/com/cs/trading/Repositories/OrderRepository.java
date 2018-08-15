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

	public List<Order> findOrdersByTraderId(int traderId) {
		return jdbcTemplate.query("select * from orders where ownerid=?", new OrderRowMapper(), traderId);
	}

	public List<Order> findOrdersBySymbol(String tickerSymbol) {
		return jdbcTemplate.query("select * from orders where tickersymbol=?", new OrderRowMapper(), tickerSymbol);
	}

	public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, double price, int quantity, String tickerSymbol, int traderId){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		String timestampString = formatter.format(timestamp);
		String orderTypeString = orderType.name();
		String statusString = status.name();
		String sideString = side.name();
		return jdbcTemplate.update("insert into orders( ordertype, status, side, timestamp, filledquantity, price, quantity, tickersymbol, ownerid) VALUES (?,?,?,?,?,?,?,?,?)",orderTypeString, statusString, sideString, timestampString, 0, price, quantity, tickerSymbol, traderId);
	}

	public int deleteOrder(int orderId) {
		return jdbcTemplate.update("update orders SET status = ? WHERE id=?","CANCELLED", orderId);
	}

	public int updateOrder(int orderId, OrderType orderType, Double price, Integer quantity) {
		if(orderType == null) {
			if(price == null) {
				return jdbcTemplate.update("update orders SET quantity = ? WHERE id=?", quantity, orderId);
			}
			else if(quantity == null)
				return jdbcTemplate.update("update orders SET price = ? WHERE id=?", price, orderId);
			else
				return jdbcTemplate.update("update orders SET price = ?, quantity = ? WHERE id=?", price, quantity, orderId);
		}
		else if(price == null) {
			if(quantity == null)
				return jdbcTemplate.update("update orders SET ordertype = ? WHERE id=?", orderType.name(), orderId);
			else
				return jdbcTemplate.update("update orders SET ordertype = ?, quantity = ? WHERE id=?", orderType.name(), quantity, orderId);
		}
		else if(quantity == null)
			return jdbcTemplate.update("update orders SET ordertype = ?, price = ? WHERE id=?", orderType.name(), price, orderId);
		else
			return jdbcTemplate.update("update orders SET ordertype = ?, price = ?, quantity = ? WHERE id=?", orderType.name(), price, quantity, orderId);
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

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
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
