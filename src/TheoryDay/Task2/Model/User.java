package TheoryDay.Task2.Model;

import TheoryDay.Task2.Enum.Role;

import java.util.Arrays;

public class User {
    private long id; // to control object
    private String email;
    private String password;
    private String name;
    private Role role;
    private Product[] basket = new Product[0];
    private static long generatedID = 1;

    public User() {
        this.id = generatedID++;
    }

    public User(String email, String password, String name, Role role) {
        this.id = generatedID++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Product[] getBasket() {
        return basket;
    }

    public void setBasket(Product[] basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", basket=" + Arrays.toString(basket);
    }
}
