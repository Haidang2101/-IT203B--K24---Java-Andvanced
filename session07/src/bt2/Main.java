package bt2;

public class Main {
    public static void main(String[] args) {

        OrderCalculator calculator =
                new OrderCalculator(new PercentageDiscount(0.1));

        double result = calculator.calculate(1000000);

        System.out.println(result);
    }
}