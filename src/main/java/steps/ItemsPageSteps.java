package steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.ItemsPage;
import pages.Product;

import java.util.List;

import static steps.BaseCucu.getDriver;

public class ItemsPageSteps {
    public final Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10).ignoring(StaleElementReferenceException.class, ElementNotVisibleException.class);


    @Step("Вводим к строке поиска \"iphone\"")
    public void searchIphone(String input) {
        try {
            new BasePage().getSearchInput().sendKeys(input);
            Assert.assertTrue(new BasePage().getSearchButton().isDisplayed());
            new BasePage().getSearchButton().click();
        } catch (StaleElementReferenceException e) {
            new BasePage().getSearchInput().sendKeys(input);
            Assert.assertTrue(new BasePage().getSearchButton().isDisplayed());
            new BasePage().getSearchButton().click();
        }
    }

    @Step("Ограничиваем цену")
    public void limitPrice(String limit) throws InterruptedException {

        try {
            for (int i = 0; i < 5; i++) {
                new ItemsPage().getLimitInput().sendKeys(Keys.BACK_SPACE);
            }
            new ItemsPage().getLimitInput().sendKeys(limit.replaceAll("1", ""));
        } catch (StaleElementReferenceException e) {
            for (int i = 0; i < 5; i++) {
                new ItemsPage().getLimitInput().sendKeys(Keys.BACK_SPACE);
            }
            new ItemsPage().getLimitInput().sendKeys(limit.replaceAll("1", ""));
        }
    }

    @Step("Выбираем приоритет выдачи поиска")
    public void voteHighPriority(String priority) {
        String input = "//span[contains(text(), '" + priority + "')]";
        WebElement checkBox = getDriver().findElement(By.xpath(input));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkBox);
        checkBox.click();
    }

    @Step("Выбираем обьем в GB")
    public void voteCheckbox(String amount) {
        String xpath = "//span[contains(text(), '" + amount + "') and @class='c2t3']";
        WebElement checkBox = getDriver().findElement(By.xpath("//span[contains(text(), '" + amount + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkBox);
//        wait.until(ExpectedConditions.visibilityOf(checkBox)).
        try {
            checkBox.click();
        } catch (StaleElementReferenceException e) {
            checkBox.click();
        }
    }

    @Step("Добавляем в корзину первые 8 нечетных  товаров")
    public void addItemsToCard() throws InterruptedException {
        for (int i = 0; i < 16; i++) {
            if (i % 2 != 0) {
                WebElement title = getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]"));
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", title);
                Thread.sleep(3000);
                getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину ')]")).click();
                Product.createProd(getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]")).getAttribute("innerHTML"));
                System.out.println("hello");
//                wait.until(ExpectedConditions.visibilityOf(title));
//                wait.until(ExpectedConditions.visibilityOf(buyButton)).click();
            }
        }
    }

    @Step("Запомнили названия в предидущем шаге")
    public void rememberItemsNames() {
        System.out.println("Запомнили названия товаров в предидущем шаге");
    }

    @Step("Нажимаем на кнопку корзины")
    public void goToBasketPage() throws InterruptedException {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", new BasePage().getBasket());
        new BasePage().getBasket().click();
        Thread.sleep(4000);
    }
}
