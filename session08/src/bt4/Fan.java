package bt4;

public class Fan implements Observer {
    public void update(int temp) {
        if (temp > 25) System.out.println("Quạt: mạnh");
        else System.out.println("Quạt: tắt");
    }
}