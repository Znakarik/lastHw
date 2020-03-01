package steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.ItemsPage;
import pages.Product;

import static steps.BaseCucu.getDriver;

public class ItemsPageSteps {

    @Step("Переходим по адресу \"{0}\"")
    public void goTo(String address) {
        getDriver().get(address);
    }

    @Step("Вводим к строке поиска \"{0}\"")
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

    @Step("Ограничиваем цену \"{0}\"")
    public void limitPrice(String limit) {
        if (BaseCucu.isFirst()) {
            for (int i = 0; i < 5; i++) {
                new ItemsPage().getLimitInput().sendKeys(Keys.BACK_SPACE);
            }
            new ItemsPage().getLimitInput().sendKeys(limit.replaceAll("1", ""));
        }
        if (BaseCucu.isSecond()) {
            ItemsPage itemsPage = new ItemsPage();
            try {
                itemsPage.getLimitInput().sendKeys(limit);
            } catch (StaleElementReferenceException e) {
                itemsPage.getLimitInput().sendKeys(limit);
            }
        }
    }

    @Step("Выбираем приоритет выдачи поиска")
    public void voteHighPriority(String priority) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]")));
        try {
            getDriver().findElement(By.xpath("//span[contains(text(), '" + priority.trim() + "')]")).click();
        } catch (WebDriverException e) {
            getDriver().findElement(By.xpath("//button[@aria-label='Закрыть сообщение']")).click();
            try {
                getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]")).click();
            } catch (WebDriverException e1) {
                getDriver().findElement(By.xpath("//span[contains(text(), '" + priority + "')]")).click();
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

    @Step("Бренды : \"{0}\", \"{1}\"")
    public void chooseBrands(String first, String second) throws InterruptedException {
        ItemsPage itemsPage = new ItemsPage();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getShowAllButton());
        itemsPage.getShowAllButton().click();
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getBrandInput());
        } catch (NoSuchElementException e) {
            itemsPage.getShowAllButton().click();
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getBrandInput());
        }
        try {
            itemsPage.getBrandInput().sendKeys(first);
        } catch (WebDriverException e) {
            itemsPage.getBrandInput().sendKeys(first);
        }
        itemsPage.getBeats().click();

        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", itemsPage.getBrandInput());
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=\"" + second + "\"", new ItemsPage().getBrandInput());
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
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        if (BaseCucu.isFirst()) {
            for (int i = 0; i < 16; i++) {
                if (i % 2 != 0) {
                    WebElement title = getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]"));
                    try {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", title);
                    } catch (StaleElementReferenceException e) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", title);
                    }
                    try {
                        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("((//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']/../../..//button)[2]"))));
                    } catch (StaleElementReferenceException e) {
                        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("((//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']/../../..//button)[2]"))));
                    }
                    try {
                        Product.createProd(getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    } catch (StaleElementReferenceException e) {
                        Product.createProd(getDriver().findElement(By.xpath("(//a[contains(@class,'tile-hover-target')and contains(text(),'iPhone')])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    }
                    try {
                        getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину ')]")).click();
                    } catch (WebDriverException e) {
                        getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину ')]")).click();
                    }
                }
            }
        }
        if (BaseCucu.isSecond()) {
            for (int i = 1; i < new ItemsPage().getBrandsTitles().size(); i++) {
                if (i % 2 == 0) {
//                    try {
//                        getDriver().findElement(By.xpath("((//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price'])/../../..//div[contains(text(),'Похожие')]"));
//                    } catch (NoSuchElementException e) {
//                        continue;
//                    }
                    try {
                        Assert.assertTrue(getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину')]")).isDisplayed());
                        getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину')]")).click();
                    } catch (WebDriverException e1) {
                        getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]/../../..//div[contains(text(),'В корзину')]")).click();
                    }
                    try {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")));
                        Product.createProd(getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    } catch (StaleElementReferenceException e) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")));
                        Product.createProd(getDriver().findElement(By.xpath("(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])[" + i + "]")).getAttribute("innerHTML"), getDriver().findElement(By.xpath("(//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']")).getText().replaceAll("\\D", ""));
                    }
                    try {
                        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("((//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']/../../..//button)[2]"))));
                    } catch (StaleElementReferenceException e) {
                        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("((//a[@data-test-id='tile-name'])[" + i + "]/../../..//span[@data-test-id='tile-price']/../../..//button)[2]"))));
                    }
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
        } catch (WebDriverException e) {
            getDriver().get("https://www.ozon.ru/cart");
        }
    }
}
