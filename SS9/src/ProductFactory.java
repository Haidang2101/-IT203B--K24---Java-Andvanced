class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extra) {
        if (type == 1) return new PhysicalProduct(id, name, price, extra);
        if (type == 2) return new PhysicalProduct(id, name, price, extra);
        return null;
    }
}