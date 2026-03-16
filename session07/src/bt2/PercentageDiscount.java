package bt2;

public class PercentageDiscount implements DiscountStrategy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double apply(double total) {
        return total - total * percent;
    }
}