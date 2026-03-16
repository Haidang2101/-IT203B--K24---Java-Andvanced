package btth;

public class Paypal implements Payment {
    @Override
    public void pay() {
        System.out.println("Paypal pay");
    }
}
