package dao;

import entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Optional<Product> findByIdForUpdate(long id) throws SQLException;
    void updateStock(long productId, int newStock) throws SQLException;
    void insert(Product product) throws SQLException;
    void update(Product product) throws SQLException;
    void delete(long id) throws SQLException;
    List<Product> findAll() throws SQLException;
}