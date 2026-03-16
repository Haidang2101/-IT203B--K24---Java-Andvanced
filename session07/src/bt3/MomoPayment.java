package bt3;

public class MomoPayment implements PaymentMethod{
    public void pay(double amount){
        System.out.println("Thanh toan momo: "+amount);
    }
}