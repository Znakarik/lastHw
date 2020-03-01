package steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import pages.BasketPage;
import pages.Product;
import util.ParserCSV;
import util.ParserJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static steps.BaseCucu.*;

public class BasketPageSteps {

    @Step("Проверяем есть ли на странице все ранее добавленные товары")
    public void checkIsBasketContainsItems() {
        if (BaseCucu.isFirst()) {
            for (Product product : Product.iPhoneList) {
                try {
                    Assert.assertTrue(getDriver().findElement(By.xpath("//span[contains(text(),'" + product.getName().trim() + "')]")).isDisplayed());
                } catch (StaleElementReferenceException e) {
                    Assert.assertTrue(getDriver().findElement(By.xpath("//span[contains(text(),'" + product.getName().trim() + "')]")).isDisplayed());
                }
            }
        }
        if (BaseCucu.isSecond()) {
            for (Product product : Product.earsList) {
                Assert.assertTrue(getDriver().findElement(By.xpath("//span[contains(text(),'" + product.getName().trim() + "')]")).isDisplayed());
            }
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
            if (BaseCucu.isFirst()) {
                for (int i = 0; i < Product.iPhoneList.size(); i++) {
                    Thread.sleep(3000);
                    getDriver().findElement(By.xpath("(//span[contains(text(),'Удалить')])[3]")).click();
                    WebElement deleteButton = getDriver().findElement(By.xpath("//div[contains(text(),'Удалить')]"));
                    deleteButton.click();
                    Thread.sleep(3000);
                }
            }
            if (BaseCucu.isSecond()) {
                for (int i = 0; i < Product.earsList.size(); i++) {
                    Thread.sleep(3000);
                    getDriver().findElement(By.xpath("(//span[contains(text(),'Удалить')])[3]")).click();
                    WebElement deleteButton = getDriver().findElement(By.xpath("//div[contains(text(),'Удалить')]"));
                    deleteButton.click();
                    Thread.sleep(3000);
                }
            }
            Thread.sleep(5000);
        } catch (NullPointerException e) {
            getDriver().get("https://www.ozon.ru/cart");
            deleteAll();
        }
    }

    @Step("Проверяем, что корзина пуста")
    public void checkBasketIsEmpty() throws InterruptedException {
        WebElement title = getDriver().findElement(By.xpath("//h1[contains(text(),'Корзина пуста')]"));
        Assert.assertTrue(title.isDisplayed());
    }
    @Attachment("Создаем файл с информацией о продуктах")
    public static byte[] generateInfo() throws IOException {
        new BasketPageSteps().createFile();
        return Files.readAllBytes(Paths.get("src/test/java/attach/parsedItems"));
    }

//    @Attachment("Создаем файл с информацией о продуктах")
//    public File generateInfo() throws IOException {
//        return new BasketPageSteps().createFile();
//    }

    public File createFile() throws IOException {
        ParserJson parserJson = new ParserJson();
        parserJson.serialise(Product.iPhoneList);
        ParserCSV parserCSV = new ParserCSV();
        parserCSV.printInfoIntoCSV();
        return parserCSV.readInfoFromCSVToTxt();
    }
}
