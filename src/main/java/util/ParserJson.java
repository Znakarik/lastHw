package util;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import pages.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserJson {
    ObjectMapper mapper = new ObjectMapper();

    public void serialise(List<Product> products) throws IOException {
        mapper.writeValue(new File("/Users/o/IdeaProjects/lastHw/src/main/resources/products.json"), products);
    }
}
