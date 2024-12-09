package TheoryDay.Task2;

import TheoryDay.Task2.DAO.Impl.ProductDaoImpl;
import TheoryDay.Task2.DAO.Impl.UserDaoImpl;
import TheoryDay.Task2.DAO.UserDao;
import TheoryDay.Task2.DataBase.DataBase;
import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Role;
import TheoryDay.Task2.Enum.Size;
import TheoryDay.Task2.Model.Product;
import TheoryDay.Task2.Model.User;
import TheoryDay.Task2.Service.Impl.ProductServiceImpl;
import TheoryDay.Task2.Service.Impl.UserServiceImpl;
import TheoryDay.Task2.Service.ProductService;
import TheoryDay.Task2.Service.UserService;
import TheoryDay.Task2.config.Validation;
import TheoryDay.Task2.exceptions.NotFoundException;
import org.ietf.jgss.GSSContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

// Online-shope
public class Main {
    static  Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static ProductDaoImpl productDao = new ProductDaoImpl();
    static ProductService productService = new ProductServiceImpl(productDao);
    static UserDao userDao = new UserDaoImpl();
    static UserService userService = new UserServiceImpl(userDao);
    static User currentUser = null;
    public static void main(String[] args) {
        userService.saveDefaultAdmin();

        while(true){
            System.out.println("""
                    Press to 1: sign up
                    Press to 2: sign in
                    Press to 3: get all
                    """);

            switch (checkValidCommand()){
                case 1 ->{
                    System.out.println("Registration: ");
                    String validEmail = isValidData("email: ", Validation.emailPattern());
                    String validPassword = isValidData("1password: ", Validation.passwordPattern());

                    String name;
                    while(true){
                        System.out.println("Enter name: ");
                        name = scannerStr.nextLine().trim();
                        if(!name.isEmpty()){
                            break;
                        }else{
                            System.out.println("Name must not be empty.");
                        }
                    }
                    String result = userService.signUp(new User(validEmail, validPassword, name, Role.CLIENT));
                    System.out.println(result);
                    break;
                }
                case 2 ->{
                    System.out.println("Sign in");
                    String validEmail = isValidData("email", Validation.emailPattern());
                    String validPassword = isValidData("password", Validation.passwordPattern());
                    try {
                        currentUser = userService.signIn(validEmail, validPassword);
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    if(currentUser.getRole().equals(Role.ADMIN)){
                        workWithAdmin();
                    }else if(currentUser.getRole().equals(Role.CLIENT)){
                        System.out.println("You are logged in as CLIENT!");
                        workWithClient();
                    }
                    break;
                }
                case 3 ->{
                    System.out.println("All users: ");
                    for (User user : userService.findAll()) {
                        System.out.println(user);
                    }
                }
            }
        }
    }
    public static void workWithClient(){
        boolean circle = true;
        while(circle) {
            System.out.println("""
                Press 1 to get all products
                Press 2 to see products with categories or sizes 
                Press 3 tot see products with the same categories and sizes
                Press 4 to add/delete by ID to favorite products     
                Press 5 to get all favorite products
                Press 6 to get favorite product by ID
                Press 7 to logout
            """);
            int num = scannerInt.nextInt();
            scannerStr.nextLine();
            switch (num) {
                case 1 -> {
                    System.out.println("All products: ");
                    System.out.println(Arrays.toString(userService.getAllProducts()));
                    break;
                }
                case 2 -> {
                    System.out.println("Select 1.category / 2.sizes");
                    num = scannerInt.nextInt();
                    scannerStr.nextLine();
                    switch (num) {
                        case 1 -> {
                            System.out.println("""
                            Select category: 
                            1. WOMAN
                            2. MAN
                            3. CHILDREN
                           """);
                            num = scannerInt.nextInt();
                            scannerStr.nextLine();
                            switch (num) {
                                case 1 -> {
                                    System.out.println("Product/s with category WOMAN: ");
                                    System.out.println(userService.getAllProductsByCategory(Category.WOMAN, currentUser));
                                    break;
                                }
                                case 2 -> {
                                    System.out.println("Product/s with category MAN: ");
                                    System.out.println(userService.getAllProductsByCategory(Category.MAN,currentUser));
                                    break;
                                }
                                case 3 -> {
                                    System.out.println("Product/s with category CHILDREN: ");
                                    System.out.println(userService.getAllProductsByCategory(Category.CHILDREN, currentUser));
                                    break;
                                }
                                default -> {
                                    System.out.println("We can't find this category.");
                                }
                            }
                        }
                        case 2->{
                            System.out.println(userService.getAllProductsBySizes(selectSize(), currentUser));
                            break;
                        }
                        default -> {
                            System.out.println("Can't find option to work with categories/sizes.");
                        }
                    }
                }
                case 3->{
                    System.out.println("Write the category and sizes of product: ");
                    System.out.println(Arrays.toString(productService.getProductsWithTheSameCategoriesAndSizes(selectCategory(), selectSize(), currentUser)));
                }
                case 4->{
                    System.out.println("Select 1.add/ 2.delete");
                    int ch = scannerInt.nextInt();
                    scannerStr.nextLine();
                    switch (ch){
                        case 1->{
                            try {
                                System.out.println("Write the ID to add: ");
                                long id = scannerInt.nextInt();
                                scannerStr.nextLine();
                                System.out.println(userService.addFavoriteProductByID(id, currentUser));
                            }catch (NumberFormatException e){
                                throw new IllegalArgumentException("Please, write number to show ID.");
                            }
                            break;
                        }
                        case 2->{
                            try {
                                System.out.println("Write the ID to delete: ");
                                long id = scannerInt.nextInt();
                                scannerStr.nextLine();
                                System.out.println(userService.deleteFavoriteProductByID(id, currentUser));
                            }catch (NumberFormatException e){
                                throw new IllegalArgumentException("Please, write number to show ID.");
                            }
                            break;
                        }
                        default -> {
                            System.out.println("We can't find this option.");
                        }
                    }
                }
                case 5->{
                    System.out.println(Arrays.toString(userService.getAllFavoriteProducts(currentUser)));
                    break;
                }
                case 6->{
                    try {
                        System.out.println("Write the ID of favorite product: ");
                        System.out.println(userService.getFavoriteProductByID(scannerInt.nextLong(), currentUser));
                    }catch (NumberFormatException e ){
                        throw new IllegalArgumentException("Please, write number to show ID.");
                    }
                    break;
                }
                case 7->{
                    circle = false;
                    break;
                }
                default -> {
                    System.out.println("Can't find this option.");
                }

            }
        }
    }
    public static void workWithAdmin(){
        System.out.println("You are logged in as ADMIN!");
        boolean choice = true;
        while(choice){
            System.out.println("""
                                    Press 1 to add product
                                    Press 2 to delete product
                                    Press 3 to update product
                                    Press 4 to get all products
                                    Press 5 to log out
                                    """);
            switch (checkValidCommand()){
                case 1 ->{
                    productService.addProductByAdmin(createProduct());
                    break;
                }
                case 2->{
                    System.out.println("Delete product, write the id: ");
                    productService.deleteProduct(scannerInt.nextLong());
                    scannerStr.nextLine();
                    break;
                }
                case 3 ->{
                    System.out.println("Update product: ");
                    System.out.println("Write the id: ");
                    long id = scannerInt.nextLong();
                    if(productService.getProductById(id)) {
                        System.out.println(productService.updateProduct(id, createProduct()));
                    }else{
                        System.out.println("Write the correct id.");
                    }
                    break;
                }
                case 4->{
                    System.out.println(Arrays.toString(productService.getAllProducts()));
                    break;
                }
                case 5->{
                    choice = false;
                    break;
                }
                default -> {
                    System.out.println("Can't find this option.");
                }
            }
        }
    }
    public static Product createProduct(){
        Product product = new Product();
        String name;
        while(true){
            System.out.println("Write the name of product: ");
            name = scannerStr.nextLine();
            if(!name.isEmpty()){
                break;
            }else{
                System.out.println("The name of product must not be empty.");
            }
        }
        product.setName(name);
        BigDecimal price = null;
        while(price == null) {
            try {
                System.out.println("Write the price of product: ");
                String str = scannerStr.nextLine();
                price = new BigDecimal(str);
                if(price.compareTo(BigDecimal.ZERO) < 0){
                    System.out.println("The price cannot be negative. Please enter correct price: ");
                    price = null;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("Please write number to show the price.");
            }
        }
        product.setPrice(price);
        scannerStr.nextLine();
        product.setSizes(selectSize());

        // amount
        try{
            System.out.println("Write amount of product: ");
            product.setAmount(scannerInt.nextInt());
        }catch (InputMismatchException e){
            throw new IllegalArgumentException("Please, use a number to show amount.");
        }



        scannerStr.nextLine();
        System.out.println("Write the color of product: ");
        product.setColor(scannerStr.nextLine());
        System.out.println("Write imgURl of product: ");
        product.setImgURL(scannerStr.nextLine());
        System.out.println("Select the category of product: ");
        product.setCategory(selectCategory());
        return product;
    }
    public static Category selectCategory(){
            System.out.println("""
                    1. Woman
                    2. Man
                    3. Child
                    """);
            switch (scannerInt.nextInt()) {
                case 1 -> {
                    return Category.WOMAN;
                }
                case 2 -> {
                    return Category.MAN;
                }
                case 3 -> {
                    return Category.CHILDREN;
                }
                default -> {
                    return null;
                }
            }
    }
    public static Size[] selectSize(){
        boolean repeat = true;
        Size[] sizes = new Size[0];
            while(repeat) {
                System.out.println("""
                        Select the size of product: 
                        1. XXS
                        2. XS
                        3. S
                        4. M
                        5. L
                        6. XL
                        7. XXL
                        8. Exit 
                        """);
                switch (checkValidCommand()) {
                    case 1 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.XXS;
                        break;
                    }
                    case 2 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.XS;
                        break;
                    }
                    case 3 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.S;
                        break;
                    }
                    case 4 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.M;
                        break;
                    }
                    case 5 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.L;
                        break;
                    }
                    case 6 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.XL;
                        break;
                    }
                    case 7 -> {
                        sizes = Arrays.copyOf(sizes, sizes.length + 1);
                        sizes[sizes.length - 1] = Size.XXL;
                        break;
                    }
                    case 8 -> {
                        repeat = false;
                        break;
                    }
                    default -> {
                        System.out.println("We can't find this size, please write existing size: ");
                    }
                }
            }
        return sizes;
    }
    public static String isValidData(String label, String pattern){
        System.out.printf("Enter the %s: ",label);
        String data;
        do{
            data = new Scanner(System.in).nextLine();
            scannerStr.nextLine();
            if(!data.matches(pattern)){
                System.out.printf("Invalid %s. Enter valid %s: ", label, label);
            }
        }while(!data.matches(pattern));

        return data;

    }
    public static int checkValidCommand(){
        int command = 0;
        boolean invalidCommand;
        System.out.println("Write the command: ");
        do{
            try {
                command = scannerInt.nextInt();
                scannerStr.nextLine();
                invalidCommand = false;
            } catch (InputMismatchException e) {
                invalidCommand = true;
                System.out.println("Invalid command.");
            }
        }while(invalidCommand);
        return command;
    }
}
