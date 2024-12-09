package TheoryDay.Task2.DAO;

import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;

public interface ProductDao {
    void addProductByAdmin(Product product);
    String deleteProduct(long id);
    void updateProduct(long id, Product product);
    Product[] getAllProducts();
    Product[] getProductsWithTheSameCategoriesAndSizes(Category category, Size[] sizes, User user);

}
