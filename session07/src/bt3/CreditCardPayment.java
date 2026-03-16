package bt3;

public class CreditCardPayment implements PaymentMethod{
    public void pay(double amount){
        System.out.println("Thanh toan the: "+amount);
    }
}