package util;

import io.cucumber.datatable.TypeReference;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import pages.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Hello", "222"));
        products.add(new Product("FFF", "222"));
        products.add(new Product("GGGG", "2222"));
        products.add(new Product("DDDD", "123"));
        products.add(new Product("Sdfff", "222"));

        Parser parser = new Parser();
        parser.serialise(products);
    }

    public void serialise(List <Product> products) throws IOException {
        mapper.writeValue(new File("/Users/o/IdeaProjects/lastHw/src/main/resources/products.json"), products);
    }
}
