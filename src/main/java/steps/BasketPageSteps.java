package steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasketPage;
import pages.Product;
import util.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import static steps.BaseCucu.*;

public class BasketPageSteps {

    @Step("Проверяем есть ли на странице все ранее добавленные товары")
    public void checkIsBasketContainsItems() {
        for (Product product : Product.productList) {
            Assert.assertTrue(getDriver().findElement(By.xpath("//span[contains(text(),'" + product.getName().trim() + "')]")).isDisplayed());
        }
    }

    @Step("Проверяем наличие текста на странице")
    public void checkIsTextExist(String text) {
        String count = new BasketPage().getYourBasket().getText().trim() + " - " + new BasketPage().getItemsCount().getText().trim().substring(0, 9);
        Assert.assertEquals(text, count);
    }

    @Step("Удаляем все товары из корзины")
    public void deleteAll() throws InterruptedException {
        try {
            for (int i = 0; i < Product.productList.size(); i++) {
                Thread.sleep(3000);
                getDriver().findElement(By.xpath("(//span[contains(text(),'Удалить')])[3]")).click();
                WebElement deleteButton = getDriver().findElement(By.xpath("//div[contains(text(),'Удалить')]"));
                deleteButton.click();
                Thread.sleep(3000);
            }
            Thread.sleep(5000);
        } catch (NullPointerException e) {
            getDriver().get("https://www.ozon.ru/cart");
            deleteAll();
        }
    }

    @Step("Проверяем, что корзина пуста")
    public  void checkBasketIsEmpty() throws InterruptedException {
        WebElement title = getDriver().findElement(By.xpath("//h1[contains(text(),'Корзина пуста')]"));
        Assert.assertTrue(title.isDisplayed());
    }

    @Step("Создаем файл с информацией о продуктах")
    public void generateInfo() throws IOException {
        new BasketPageSteps().createFile();
    }

//    @Attachment
//    public static byte[] getFile(String pack){
//
//    }

    public void createFile() throws IOException {
        Parser parser = new Parser();
        parser.serialise(Product.productList);
    }

}
