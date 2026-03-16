package bt3;

public class Main {
    public static void main(String[] args) {

        PaymentProcessor p =
                new PaymentProcessor(new MomoPayment());

        p.process(750000);
    }
}