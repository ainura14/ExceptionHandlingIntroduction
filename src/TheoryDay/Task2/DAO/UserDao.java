package TheoryDay.Task2.DAO;

import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;

public interface UserDao {
    void save(User user);

    User[] findAll();

    Product[] getAllProducts();

    Product getAllProductsByCategory(Category category, User user);

    Product getAllProductsBySizes(Size[] sizes, User user);

    String addFavoriteProductByID(long id, User user);

    String deleteFavoriteProductByID(long id, User user);

    Product[] getAllFavoriteProducts(User user);

    Product getFavoriteProductByID(long id, User user);

}
