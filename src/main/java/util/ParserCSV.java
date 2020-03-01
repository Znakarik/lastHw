package util;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import pages.Product;
import steps.BaseCucu;

import java.io.*;
import java.util.*;

import static steps.BaseCucu.isFirst;
import static steps.BaseCucu.isSecond;

@Getter
public class ParserCSV {
    File getItems = new File("src/test/java/attach/getItems.csv");
    File parsedItems = new File("src/test/java/attach/parsedItems");
    FileWriter out = new FileWriter(getItems, true);
    String[] HEADERS = {"Name", "Price"};
    List<Product> productList = new ArrayList<>();
    Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(getItems)));

    public ParserCSV() throws IOException {
    }

    public void printInfoIntoCSV() throws IOException {
        if (isFirst()) {
            /** Если пишем впервые в файл */
            if (getItems.length() == 0) {
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
                    Product.iPhoneList.forEach(product -> {
                        try {
                            printer.printRecord(product.getName(), product.getPrice());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            if (getItems.length() > 0) {
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                    Product.iPhoneList.forEach(product -> {
                        try {
                            printer.printRecord(product.getName(), product.getPrice());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
        if (BaseCucu.isSecond()) {
            if (getItems.length() == 0) {
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
                    Product.earsList.forEach(product -> {
                        try {
                            printer.printRecord(product.getName(), product.getPrice());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            if (getItems.length() > 0) {
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                    Product.earsList.forEach(product -> {
                        try {
                            printer.printRecord(product.getName(), product.getPrice());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//        ParserCSV parserCSV = new ParserCSV();
//        List<Product> productList = new ArrayList<>();
//        List<Product> AUTHOR_BOOK_MAP = new ArrayList<Product>() {
//            {
//                add(new Product("Dan Simmons", 1));
//                add(new Product("Douglas Adams", 2));
//            }
//        };
//        String[] HEADERS = {"Name", "Price"};
//        File file = new File("test.csv");
//        FileWriter out = new FileWriter("test.csv", true);
//
//        if (file.length() == 0) {
//            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS)
//            )) {
//                AUTHOR_BOOK_MAP.forEach((product) -> {
//                    try {
//                        printer.printRecord(product.getName(), product.getPrice());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        }
//        if (file.length() > 0) {
//            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
//                AUTHOR_BOOK_MAP.forEach((product) -> {
//                    try {
//                        printer.printRecord(product.getName(), product.getPrice());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        }
//
//        Reader in = new FileReader("test.csv");
//        Iterable<CSVRecord> records = records = CSVFormat.DEFAULT
//                .withHeader(HEADERS)
//                .withFirstRecordAsHeader()
//                .withSkipHeaderRecord()
//                .parse(in);
//
//        for (CSVRecord record : records) {
//            String name = record.get("Name");
//            Integer price = null;
//            try {
//                price = Integer.parseInt(record.get("Price").trim());
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            productList.add(new Product(name, price));
//        }
//        Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
//        productList.sort(comparator);
//
//        FileWriter writer = new FileWriter("productsTest.txt", true);
//        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
//                .withSkipHeaderRecord())) {
//            productList.forEach(product -> {
//                try {
//                    printer.printRecord(product.getName(), product.getPrice());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            try {
//                writer.write("Самый дорогой продукт - " + productList.get(productList.size() - 1).getName() + ", цена - " + productList.get(productList.size() - 1).getPrice() + " \n");
//            } catch (ArrayIndexOutOfBoundsException e) {
//                e.printStackTrace();
//            }
//        }
//        /** Main end */
//    }

    public File readInfoFromCSVToTxt() throws IOException {
        Reader in = new FileReader(getItems);
        Iterable<CSVRecord> records = null;

        if (isFirst()) {
            records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .withSkipHeaderRecord()
                    .parse(in);
        }
        if (isSecond()) {
            records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .withSkipHeaderRecord()
                    .parse(in);
        }
        assert records != null;
        for (CSVRecord record : records) {
            String name = record.get("Name");
            Integer price = null;
            try {
                price = Integer.parseInt(record.get("Price").trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            productList.add(new Product(name, price));
        }

        productList.sort(comparator);

        FileWriter writer = new FileWriter(parsedItems, true);
        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withSkipHeaderRecord())) {
            productList.forEach(product -> {
                try {
                    printer.printRecord("\n" + product.getName(), product.getPrice() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if (isSecond()) {
                try {
                    writer.write("Самый дорогой продукт - " + productList.get(productList.size() - 1).getName() + ", цена - " + productList.get(productList.size() - 1).getPrice());
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
        return parsedItems;
    }
}
