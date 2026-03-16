package bt1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("SP01","Laptop",15000000);
        Product p2 = new Product("SP02","Chuot",300000);

        Customer c = new Customer("Nguyen Van A","a@example.com");

        Order order = new Order("ORD01",c, Arrays.asList(p1,p2));

        OrderCalculator calculator = new OrderCalculator();
        double total = calculator.calculate(order);

        System.out.println("Tong tien: "+total);

        OrderRepository repo = new OrderRepository();
        repo.save(order);

        EmailService email = new EmailService();
        email.send(c.getEmail(),"Don hang "+order.getId()+" da duoc tao");
    }
}