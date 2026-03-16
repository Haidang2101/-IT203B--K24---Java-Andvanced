package bt1;

public class OrderCalculator {
    public double calculate(Order order){
        return order.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }
}