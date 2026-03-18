import java.util.ArrayList;
import java.util.List;

class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> list = new ArrayList<>();

    private ProductDatabase() {}

    public static ProductDatabase getInstance() {
        if (instance == null) instance = new ProductDatabase();
        return instance;
    }

    public void add(Product p) { list.add(p); }

    public List<Product> getAll() { return list; }

    public void delete(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id.equalsIgnoreCase(id)) {
                list.remove(i);
                System.out.println("Đã xoá!");
                return;
            }
        }
        System.out.println("Không tìm thấy ID để xoá!");
    }

    public Product findById(String id) {
        for (Product p : list) {
            if (p.id.equalsIgnoreCase(id)) return p;
        }
        return null;
    }
}