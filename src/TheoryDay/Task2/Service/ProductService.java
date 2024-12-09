package TheoryDay.Task2.Service;

import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;

public interface ProductService {
    // admin's methods
    String addProductByAdmin(Product product);
    void deleteProduct(long id);
    String updateProduct(long id, Product product);
    boolean getProductById(long id);
    Product[] getAllProducts();
    Product[] getProductsWithTheSameCategoriesAndSizes(Category category, Size[] sizes, User user);
}
