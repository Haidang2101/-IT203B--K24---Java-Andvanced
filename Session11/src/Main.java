import utils.DatabaseConection;

public class Main{
    static void main(String[] args) throws ClassNotFoundException {
        System.out.println("connect");
        DatabaseConection.openConnection();


        System.out.println("connected");
    }
}