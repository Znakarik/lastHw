package pages;

import lombok.Data;
import steps.BaseCucu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
public class Product implements Comparable {
    public static List<Product> iPhoneList = new ArrayList<>();
    public static List<Product> earsList = new ArrayList<>();
    String name;
    Integer price;

    public Product(String name, String price) {
        this.name = name;
        this.price = Integer.parseInt(price.replaceAll(" ", ""));
    }

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public static void createProd(String name, String price) {
        if (BaseCucu.isFirst()) {
            iPhoneList.add(new Product(name, price));
        }
        if (BaseCucu.isSecond()) {
            earsList.add(new Product(name, price));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;
        return price.compareTo(product.price);
    }
}
