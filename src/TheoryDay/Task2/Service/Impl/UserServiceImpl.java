package TheoryDay.Task2.Service.Impl;

import TheoryDay.Task2.DataBase.DataBase;
import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Role;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;
import TheoryDay.Task2.DAO.UserDao;
import TheoryDay.Task2.Service.UserService;
import TheoryDay.Task2.config.Validation;
import TheoryDay.Task2.exceptions.InvalidData;
import TheoryDay.Task2.exceptions.NotFoundException;

public class UserServiceImpl implements UserService{
private final UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public String signUp(User user) {
        if(!Validation.checkEmail(user.getEmail())){
            throw new InvalidData("Invalid email");
        }else{
            User[] users = userDao.findAll();
            for (User u : users) {
                if(u.getEmail().equals(user.getEmail())){
                    throw new IllegalArgumentException("Email already exists!");
                }
            }
        }

        if(!Validation.checkPassword(user.getPassword())){
            throw new InvalidData("Invalid password.");
        }
        userDao.save(user);
        return "Successfully saved user with email: " + user.getEmail();
    }

    @Override
    public User[] findAll(){
        return DataBase.users;
    }

    @Override
    public User signIn(String email, String password) {
        for (User user : findAll()) {
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        throw new NotFoundException("User not found!");
    }

    @Override
    public void saveDefaultAdmin() {
        userDao.save(new User("admin@gmail.com", "Admin123!", "Online Shop", Role.ADMIN));
    }

    @Override
    public Product[] getAllProducts() {
        return userDao.getAllProducts();
    }

    @Override
    public Product getAllProductsByCategory(Category category, User user) {
        return userDao.getAllProductsByCategory(category, user);
    }

    @Override
    public Product getAllProductsBySizes(Size[] sizes, User user) {
        return userDao.getAllProductsBySizes(sizes, user);
    }

    @Override
    public String addFavoriteProductByID(long id, User user) {
        Product productToAdd = null;
        for (Product allProduct : DataBase.products) {
            if (allProduct.getId() == id) {
                productToAdd = allProduct;
                break;
            }
        }

        if (productToAdd == null) {
            return "We can't find this id " + id + " to add favorite product.";
        }

        for (Product product : user.getBasket()) {
            if (product.getId() == id) {
                return "This product has already been added to the favorite basket.";
            }
        }
        userDao.addFavoriteProductByID(id, user);
        return "Product with ID: " + id + " was successfully added to favorite basket.";
    }


    @Override
    public String deleteFavoriteProductByID(long id, User user) {
        for (User u : DataBase.users) {
            if(u.equals(user)){
                Product[] products = u.getBasket();
                for (Product p : products) {
                    if(p.getId() == id){
                        return userDao.deleteFavoriteProductByID(id, u);
                    }
                }
            }
        }
        return "We can't find this id " + id + " to delete favorite product.";
    }

    @Override
    public Product[] getAllFavoriteProducts(User user) {
        return userDao.getAllFavoriteProducts(user);
    }

    @Override
    public Product getFavoriteProductByID(long id, User user) {
        return userDao.getFavoriteProductByID(id, user);
    }
}

