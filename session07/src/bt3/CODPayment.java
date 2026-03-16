package bt3;

public class CODPayment implements PaymentMethod{
    public void pay(double amount){
        System.out.println("Thanh toan COD: "+amount);
    }
}