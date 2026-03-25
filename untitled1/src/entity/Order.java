package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private long id;
    private long userId;
    private LocalDateTime orderTime;
    private BigDecimal totalAmount;
    private String status;

    public Order() {}

    public Order(long id, long userId, LocalDateTime orderTime, BigDecimal totalAmount, String status) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}