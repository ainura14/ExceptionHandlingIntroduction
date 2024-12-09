package TheoryDay.Task2.DAO.Impl;

import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;
import TheoryDay.Task2.DAO.UserDao;
import TheoryDay.Task2.DataBase.DataBase;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        DataBase.users = Arrays.copyOf(DataBase.users, DataBase.users.length + 1);
        DataBase.users[DataBase.users.length - 1] = user;
    }

    @Override
    public User[] findAll() {
        return DataBase.users;
    }

    @Override
    public Product[] getAllProducts() {
        return DataBase.products;
    }

    @Override
    public Product getAllProductsByCategory(Category category, User user) {
        for (Product product : user.getBasket()) {
            if(product.getCategory().equals(category)){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product getAllProductsBySizes(Size[] sizes, User user) {
        for (Product product : user.getBasket()) {
            if(Arrays.equals(product.getSizes(), sizes)){
                return product;
            }
        }
        return null;
    }

    @Override
    public String addFavoriteProductByID(long id, User user) {
        Product[] products = new Product[0];
        for (Product product : DataBase.products) {
            if(product.getId() == id){
                products = Arrays.copyOf(products, products.length + 1);
                products[products.length - 1] = product;
                user.setBasket(products);
            }
        }
        return "Successfully added product to favorite basket.";
    }

    @Override
    public String deleteFavoriteProductByID(long id, User user) {
        Product[] products = user.getBasket();
        int len = products.length;
        for (int i = 0; i < len; i++) {
            if(products[i].getId() == id){
                for (int j = i; j < len - 1; j++) {
                    products[j] = products[j + 1];
                }
            }
            len--;
        }
        products = Arrays.copyOf(products, products.length - 1);
        user.setBasket(products);

        return "Successfully delete product with ID " + id + " from favorite basket.";
    }

    @Override
    public Product[] getAllFavoriteProducts(User user) {
        return user.getBasket();
    }

    @Override
    public Product getFavoriteProductByID(long id, User user) {
        Product[] products = user.getBasket();
        for (Product product : products) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
