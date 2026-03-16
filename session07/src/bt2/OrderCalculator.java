package bt2;

public class OrderCalculator {

    private DiscountStrategy strategy;

    public OrderCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double total){
        return strategy.apply(total);
    }
}