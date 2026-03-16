package bt2;

public class NoDiscount implements DiscountStrategy{
    public double apply(double total){
        return total;
    }
}