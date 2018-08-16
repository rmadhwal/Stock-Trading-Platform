package com.cs.trading.Repositories;


import com.cs.trading.Models.OrderType;
import com.cs.trading.Models.Status;
import com.cs.trading.Models.Order;
import com.cs.trading.Models.Side;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public List<Order> findOrdersGroupedBySide() {
		return jdbcTemplate.query("select * from orders group by side", new OrderRowMapper());
	}

	public List<Order> findOrdersGroupedByType() {
		return jdbcTemplate.query("select * from orders group by type", new OrderRowMapper());
	}

	public List<Order> findOrdersGroupedByStatus() {
		return jdbcTemplate.query("select * from orders group by status", new OrderRowMapper());
	}


	public List<Order> findOrdersByTraderId(int traderId) {
		return jdbcTemplate.query("select * from orders where ownerid=?", new OrderRowMapper(), traderId);
	}

	public List<Order> findOrdersBySide(Side side) {
		return jdbcTemplate.query("select * from orders where side=?", new OrderRowMapper(), side.name());
	}

	public List<Order> findOrdersByType(OrderType type) {
		return jdbcTemplate.query("select * from orders where ordertype=?", new OrderRowMapper(), type.name());
	}

	public List<Order> findOrdersByStatus(Status status) {
		return jdbcTemplate.query("select * from orders where status=?", new OrderRowMapper(), status.name());
	}

	public List<Order> findOrdersBySymbol(String tickerSymbol) {
		return jdbcTemplate.query("select * from orders where tickersymbol=?", new OrderRowMapper(), tickerSymbol);
	}

	public int placeOrder(OrderType orderType, Status status, Side side, Date timestamp, int filledQuantity, Double price, int quantity, String tickerSymbol, int traderId){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
		String timestampString = formatter.format(timestamp);
		String orderTypeString = orderType.name();
		String statusString = status.name();
		String sideString = side.name();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String INSERT_SQL = "insert into orders( ordertype, status, side, timestamp, filledquantity, price, quantity, tickersymbol, ownerid) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
								@Override
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps =
											connection.prepareStatement(INSERT_SQL, new String[] {"id"});
									ps.setString(1,orderTypeString);
									ps.setString(2,statusString);
									ps.setString(3,sideString);
									ps.setString(4,timestampString);
									ps.setInt(5,0);
									ps.setDouble(6,price);
									ps.setInt(7,quantity);
									ps.setString(8,tickerSymbol);
									ps.setInt(9,traderId);
									return ps;
								}
							},
				keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int changeOrderStatus(int orderId, Status status) {
		return jdbcTemplate.update("update orders SET status = ? WHERE id=?",status.name(), orderId);
	}

	public int updateOrder(Order order) {
		return jdbcTemplate.update("update orders SET filledquantity = ?, ordertype = ?, price = ?, quantity = ?, status = ? WHERE id=?", order.getFilledQuantity(), order.getOrderType().name(), order.getPrice(), order.getQuantity(), order.getStatus().name(), order.getId());
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
