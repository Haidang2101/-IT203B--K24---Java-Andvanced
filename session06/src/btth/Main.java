package btth;

public class Main {
    static void main(String[] args) {
        Thread b1=new Thread(new BoxOffice("b1"));
        Thread b2=new Thread(new BoxOffice("b2"));

        b1.start();
        b2.start();

    }
}
