package btth;

import java.io.Serializable;

public class BoxOffice implements Runnable{
    private String name;
    static int quantity=10;
    public BoxOffice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        BoxOffice.quantity = quantity;
    }
    public static synchronized void sell(String name){
        if(quantity>0){
            System.out.println("BoxOffice "+name+" đã bán 1 vé, còn lại "+--quantity);
        }
    }
    @Override
    public void run() {
        while(quantity>0){
            sell(name);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
