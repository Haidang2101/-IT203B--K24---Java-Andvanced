package bt3;

public class PaymentProcessor {

    private PaymentMethod method;

    public PaymentProcessor(PaymentMethod method) {
        this.method = method;
    }

    public void process(double amount){
        method.pay(amount);
    }
}