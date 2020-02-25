package steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.ItemsPage;
import pages.Product;

import static steps.BaseCucu.getDriver;

public class ItemsPageSteps {

    @Step("Переходим по адресу \"(.+)\"$")
    public void goTo(String address) {
        getDriver().get(address);
    }

    @Step("Вводим к строке поиска \"(.+)\"$")
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

    @Step("Ограничиваем цену \"(.+)\"$")
    public void limitPrice(String limit) {
        if (BaseCucu.isFirst()) {
            for (int i = 0; i < 5; i++) {
                new ItemsPage().getLimitInput().sendKeys(Keys.BACK_SPACE);
            }
            new ItemsPage().getLimitInput().sendKeys(limit.replaceAll("1", ""));
        }
        if (BaseCucu.isSecond()) {
            ItemsPage itemsPage = new ItemsPage();
            itemsPage.getLimitInput().sendKeys(limit);
        }
    }

    @Step("Выбираем приоритет выдачи поиска")
    public void voteHighPriority(String priority) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]")));
        try {
            getDriver().findElement(By.xpath("//span[contains(text(), '" + priority.trim() + "')]")).click();
        } catch (StaleElementReferenceException e) {
            try {
                getDriver().findElement(By.xpath("//div[@class='a1']//button")).click();
                getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]/../../..//input/..")).click();
            } catch (WebDriverException e1) {

                getDriver().findElement(By.xpath("//div[@class='a1']//button")).click();
                getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]/../../..//input/..")).click();
            }
        }
    }

    @Step("Выбираем обьем в GB")
    public void voteCheckbox(String amount) {
        WebElement checkBox = getDriver().findElement(By.xpath("//span[contains(text(), '" + amount + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", checkBox);
        try {
            checkBox.click();
        } catch (StaleElementReferenceException e) {
            checkBox.click();
        }
    }

    @Step("Бренды : \"(.+)\", \"(.+)\"")
    public void chooseBrands(String first, String second) throws InterruptedException {
        ItemsPage itemsPage = new ItemsPage();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getShowAllButton());
        itemsPage.getShowAllButton().click();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getBrandInput());
        try {
            Thread.sleep(4000);
            itemsPage.getBrandInput().sendKeys(first);
        } catch (WebDriverException | InterruptedException e) {
            itemsPage.getBrandInput().sendKeys(first);
        }
        itemsPage.getBeats().click();

        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getBrandInput());
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=\"" + second + "\"", new ItemsPage().getBrandInput());
            Thread.sleep(5000);
            new ItemsPage().getSamsung().click();
        } catch (NoSuchElementException e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("(//div[contains(text(),'Бренды')]/following-sibling::div//input)[1]")));
            for (int i = 0; i < 6; i++) {
                getDriver().findElement(By.xpath("(//div[contains(text(),'Бренды')]/following-sibling::div//input)[1]")).sendKeys(Keys.BACK_SPACE);
            }
            getDriver().findElement(By.xpath("(//div[contains(text(),'Бренды')]/following-sibling::div//input)[1]")).sendKeys(second);
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=\"" + second + "\"", getDriver().findElement(By.xpath("(//div[contains(text(),'Бренды')]/following-sibling::div//input)[1]")));
            Thread.sleep(5000);
            itemsPage.getSamsung().click();
        }
    }

    @Step("Добавляем в корзину первые 8 нечетных  товаров")
    public void addItemsToCard() throws InterruptedException {
        if (BaseCucu.isFirst()) {
            for (int i = 0; i < 16; i++) {
                if (i % 2 != 0) {
                    WebElement title = getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]"));
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", title);
                    Thread.sleep(3000);
                    getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину ')]")).click();
                    try {
                        Product.createProd(getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    } catch (StaleElementReferenceException e) {
                        Product.createProd(getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    }
                }
            }
        }
        if (BaseCucu.isSecond()) {
            for (int i = 1; i < new ItemsPage().getBrandsTitles().size(); i++) {
                if (i % 2 == 0) {
                    Thread.sleep(3000);
                    try {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")));
                        Product.createProd(getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    } catch (StaleElementReferenceException e) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")));
                        Product.createProd(getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    }
                    Thread.sleep(3000);
                    getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину')]")).click();
                }
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
        try {
            new BasePage().getBasket().click();
            Thread.sleep(4000);
        } catch (WebDriverException e) {
            new BasePage().getBasket().click();
            Thread.sleep(4000);
        }
    }
}
