package pages;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Product {
    public static List<Product> productList = new ArrayList<>();
    String name;

    public Product(String name) {
        this.name = name;
    }

    public static void createProd(String name){
    productList.add(new Product(name));
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
}
