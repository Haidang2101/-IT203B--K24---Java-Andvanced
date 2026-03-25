package dao;

import dto.OrderItemRequest;
import entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    long placeOrder(long userId, List<OrderItemRequest> items) throws SQLException;
    List<Order> getTopBuyers(int limit) throws SQLException;
    java.math.BigDecimal getCategoryRevenue(long categoryId) throws SQLException;
}