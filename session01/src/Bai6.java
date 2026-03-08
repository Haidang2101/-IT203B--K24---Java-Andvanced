class Users {
    private String name;

    public void setName(String name) {
        if (name == null) {
            System.out.println("[ERROR] name-null");
            return;
        }
        this.name = name;
    }
}

public class Bai6 {
    public static void main(String[] args) {
        Users u = new Users();
        u.setName(null);
    }
}