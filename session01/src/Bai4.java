import java.io.IOException;

class Service {

    public void saveToFile() throws IOException {
        throw new IOException("Lỗi ghi file");
    }

    public void processUserData() throws IOException {
        saveToFile();
    }
}

public class Bai4 {
    public static void main(String[] args) {
        Service s = new Service();

        try {
            s.processUserData();
        } catch (IOException e) {
            System.out.println("Xảy ra lỗi: " + e.getMessage());
        }
    }
}