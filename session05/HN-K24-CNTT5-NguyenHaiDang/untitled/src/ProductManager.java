import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {

    private List<Product> productList = new ArrayList<>();


    public void addProduct(Product p) throws InvalidProductException {

        for (Product product : productList) {
            if (product.getId() == p.getId()) {
                throw new InvalidProductException("ID san pham da ton tai!");
            }
        }

        productList.add(p);
        System.out.println("Them san pham thanh cong!");
    }


    public void displayProducts() {

        if (productList.isEmpty()) {
            System.out.println("Danh sach rong!");
            return;
        }

        System.out.println("ID\tTen\tGia tien\tSo luong\tLoai");

        for (Product p : productList) {
            System.out.println(
                    p.getId() + "\t" +
                            p.getName() + "\t" +
                            p.getPrice() + "\t" +
                            p.getQuantity() + "\t\t" +
                            p.getCategory()
            );
        }
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {

        Optional<Product> result = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (result.isPresent()) {
            result.get().setQuantity(newQuantity);
            System.out.println("Cap nhat thanh cong!");
        } else {
            throw new InvalidProductException("Khong tim thay san pham!");
        }
    }


    public void deleteOutOfStock() {

        productList.removeIf(p -> p.getQuantity() == 0);

        System.out.println("Da xoa san pham het hang!");
    }

}