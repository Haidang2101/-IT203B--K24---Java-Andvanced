package dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReportDAO {
    List<Map<String, Object>> getTopBuyers(int limit) throws SQLException;
    BigDecimal getCategoryRevenue(long categoryId) throws SQLException;
}