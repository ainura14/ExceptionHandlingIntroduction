package TheoryDay.Task2.DAO.Impl;

import TheoryDay.Task2.DAO.ProductDao;
import TheoryDay.Task2.DataBase.DataBase;
import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {


    @Override
    public void addProductByAdmin(Product product) {
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length + 1);
        DataBase.products[DataBase.products.length - 1] = product;
    }

    @Override
    public String deleteProduct(long id) {
        int len = DataBase.products.length;
        for (int i = 0; i < len; i++) {
            if(DataBase.products[i].getId() == id){
                for (int j = i; j < len - 1; j++) {
                    DataBase.products[j] = DataBase.products[j + 1];
                }
            }
            len--;
        }
        DataBase.products = Arrays.copyOf(DataBase.products, DataBase.products.length - 1);
        return "Successfully deleted product";
    }

    @Override
    public void updateProduct(long id, Product product) {
        for (Product product1 : DataBase.products) {
            if(product1.getId() == id){
                product1.setCategory(product.getCategory());
                product1.setColor(product.getColor());
                product1.setImgURL(product.getImgURL());
                product1.setAmount(product.getAmount());
                product1.setPrice(product.getPrice());
                product1.setSizes(product.getSizes());
                product1.setName(product.getName());
                break;
            }
        }
    }

    @Override
    public Product[] getAllProducts() {
        return DataBase.products;
    }

    @Override
    public Product[] getProductsWithTheSameCategoriesAndSizes(Category category, Size[] sizes, User user) {
        Product[] products = new Product[0];
        for (Product product : user.getBasket()) {
            if(product.getCategory().equals(category)){
                for (Size size : product.getSizes()) {
                    if(Arrays.asList(sizes).contains(size)){
                        products = Arrays.copyOf(products, products.length + 1);
                        products[products.length - 1] = product;
                        break;
                    }
                }
            }
        }
        if (products.length == 0) {
            return null;
        }
        return products;
    }
}
