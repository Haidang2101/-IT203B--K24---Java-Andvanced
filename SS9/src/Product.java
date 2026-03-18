abstract class Product {
    String id, name;
    double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract void displayInfo();
}

class PhysicalProduct extends Product {
    double weight;
    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }
    @Override
    public void displayInfo() {
        System.out.println("[Vật lý] ID: " + id + ", Tên: " + name + ", Giá: " + price + ", Nặng: " + weight + "kg");
    }
}

class DigitalProduct extends Product {
    double size;
    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }
    @Override
    public void displayInfo() {
        System.out.println("[Số] ID: " + id + ", Tên: " + name + ", Giá: " + price + ", Dung lượng: " + size + "MB");
    }
}