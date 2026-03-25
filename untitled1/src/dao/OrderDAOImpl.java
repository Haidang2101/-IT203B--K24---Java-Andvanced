package dao;

import dto.OrderItemRequest;
import utils.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public long placeOrder(long userId, List<OrderItemRequest> items) throws SQLException {
        String selectProductSql = "SELECT id, price, stock FROM products WHERE id = ? FOR UPDATE";
        String insertOrderSql = "INSERT INTO orders(user_id, order_time, total_amount, status) VALUES (?, ?, ?, ?)";
        String insertDetailSql = "INSERT INTO order_details(order_id, product_id, quantity, unit_price, line_total) VALUES (?, ?, ?, ?, ?)";
        String updateStockSql = "UPDATE products SET stock = stock - ? WHERE id = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnectionManager.getInstance().getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            BigDecimal totalAmount = BigDecimal.ZERO;

            // 1) Check stock + calculate total
            for (OrderItemRequest item : items) {
                try (PreparedStatement ps = conn.prepareStatement(selectProductSql)) {
                    ps.setLong(1, item.getProductId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Product not found: " + item.getProductId());
                        }
                        int stock = rs.getInt("stock");
                        BigDecimal price = rs.getBigDecimal("price");

                        if (stock < item.getQuantity()) {
                            throw new SQLException("Hết hàng: productId=" + item.getProductId());
                        }

                        totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(item.getQuantity())));
                    }
                }
            }

            // 2) Create order
            long orderId;
            try (PreparedStatement ps = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, userId);
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                ps.setBigDecimal(3, totalAmount);
                ps.setString(4, "SUCCESS");
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Cannot create order");
                    }
                    orderId = keys.getLong(1);
                }
            }

            // 3) Insert order details with batch
            try (PreparedStatement detailPs = conn.prepareStatement(insertDetailSql);
                 PreparedStatement stockPs = conn.prepareStatement(updateStockSql);
                 PreparedStatement selectPricePs = conn.prepareStatement(selectProductSql)) {

                for (OrderItemRequest item : items) {
                    selectPricePs.setLong(1, item.getProductId());
                    BigDecimal price;

                    try (ResultSet rs = selectPricePs.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Product not found: " + item.getProductId());
                        }
                        price = rs.getBigDecimal("price");
                    }

                    BigDecimal lineTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

                    detailPs.setLong(1, orderId);
                    detailPs.setLong(2, item.getProductId());
                    detailPs.setInt(3, item.getQuantity());
                    detailPs.setBigDecimal(4, price);
                    detailPs.setBigDecimal(5, lineTotal);
                    detailPs.addBatch();

                    stockPs.setInt(1, item.getQuantity());
                    stockPs.setLong(2, item.getProductId());
                    stockPs.addBatch();
                }

                detailPs.executeBatch();
                stockPs.executeBatch();
            }

            conn.commit();
            return orderId;

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    throw new SQLException("Rollback failed", rollbackEx);
                }
            }
            if (e instanceof SQLException) throw (SQLException) e;
            throw new SQLException("Place order failed", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public java.util.List<entity.Order> getTopBuyers(int limit) throws SQLException {
        String sql = "{CALL SP_GetTopBuyers(?)}";
        java.util.List<entity.Order> result = new java.util.ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, limit);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    entity.Order o = new entity.Order();
                    o.setUserId(rs.getLong("user_id"));
                    o.setTotalAmount(rs.getBigDecimal("total_spent"));
                    result.add(o);
                }
            }
        }
        return result;
    }

    @Override
    public BigDecimal getCategoryRevenue(long categoryId) throws SQLException {
        String sql = "{? = CALL FUNC_CalculateCategoryRevenue(?)}";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.DECIMAL);
            cs.setLong(2, categoryId);
            cs.execute();
            return cs.getBigDecimal(1);
        }
    }
}