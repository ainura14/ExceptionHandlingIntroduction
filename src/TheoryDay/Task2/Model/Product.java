package TheoryDay.Task2.Model;

import TheoryDay.Task2.Enum.Category;
import TheoryDay.Task2.Enum.Size;

import java.math.BigDecimal;
import java.util.Arrays;

public class Product {
    private long id;
    private String name;
    private BigDecimal price;
    private Size[] sizes;
    private int amount;
    private String color;
    private String imgURL;
    private Category category;
    private static long incrementId = 1;

    public Product() {
        this.id = incrementId++;
    }

    public Product(String name, BigDecimal price, Size[] sizes, int amount, String color, String imgURL, Category category) {
        this.id = incrementId++;
        this.name = name;
        this.price = price;
        this.sizes = sizes;
        this.amount = amount;
        this.color = color;
        this.imgURL = imgURL;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size[] getSizes() {
        return sizes;
    }

    public void setSizes(Size[] sizes) {
        this.sizes = sizes;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sizes=" + Arrays.toString(sizes) +
                ", amount=" + amount +
                ", color='" + color + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", category=" + category +
                '}';
    }
}
