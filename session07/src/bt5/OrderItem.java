package bt5;

public class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product p,int q){
        product=p;
        quantity=q;
    }

    public double total(){
        return product.price*quantity;
    }
}