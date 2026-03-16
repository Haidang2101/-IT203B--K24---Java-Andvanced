package btth;

public class CreditCard implements Payment {
    @Override
    public void pay() {
        System.out.println("CreditCard is paying");
    }
}
