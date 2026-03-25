package dao;

import entity.Product;
import utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public Optional<Product> findByIdForUpdate(long id) throws SQLException {
        String sql = "SELECT id, name, category_id, price, stock FROM products WHERE id = ? FOR UPDATE";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                );
                return Optional.of(p);
            }
            return Optional.empty();
        }
    }

    @Override
    public void updateStock(long productId, int newStock) throws SQLException {
        String sql = "UPDATE products SET stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setLong(2, productId);
            ps.executeUpdate();
        }
    }

    @Override
    public void insert(Product product) throws SQLException {
        String sql = "INSERT INTO products(name, category_id, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setLong(2, product.getCategoryId());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, category_id = ?, price = ?, stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setLong(2, product.getCategoryId());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setLong(5, product.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT id, name, category_id, price, stock FROM products";
        List<Product> list = new ArrayList<>();
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("category_id"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                ));
            }
        }
        return list;
    }
}