package TheoryDay.Task2.Service.Impl;

import TheoryDay.Task2.DAO.ProductDao;
import TheoryDay.Task2.DataBase.DataBase;
import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;
import TheoryDay.Task2.Service.ProductService;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public String addProductByAdmin(Product product) {
        productDao.addProductByAdmin(product);
        return "Successfully added product.";
    }

    @Override
    public void deleteProduct(long id) {
        System.out.println(productDao.deleteProduct(id));
    }

    @Override
    public String updateProduct(long id, Product product) {
        for (Product product1 : DataBase.products) {
            if(product1.getId() != id){
                return "We can't find the product with this id " + id;
            }
        }
        productDao.updateProduct(id, product);
        return "Successfully updated";
    }

    @Override
    public boolean getProductById(long id) {
        for (Product product : DataBase.products) {
            if(product.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public Product[] getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product[] getProductsWithTheSameCategoriesAndSizes(Category category, Size[] sizes, User user) {
        return productDao.getProductsWithTheSameCategoriesAndSizes(category, sizes, user);
    }
}
