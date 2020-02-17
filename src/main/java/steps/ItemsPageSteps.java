package steps;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.ItemsPage;

import static pages.BasePage.click;

public class ItemsPageSteps {

    @Step("Вводим к строке поиска \"iphone\"")
    public void searchIphone(String input) {
        new BasePage().getSearchInput().sendKeys(input);
        Assert.assertTrue(new BasePage().getSearchButton().isDisplayed());
        new BasePage().getSearchButton().click();
    }

    @Step("Ограничиваем цену")
    public void limitPrice(String limit) throws InterruptedException {
        new ItemsPage().getLimitInput().clear();
        new ItemsPage().getLimitInput().sendKeys(limit);
    }

    @Step("Выбираем приоритет выдачи поиска")
    public void voteHighPriority(String priority) {
        new ItemsPage().getPriorityCheckbox().sendKeys(priority);
    }

    @Step("Выбираем обьем в GB")
    public void vote3GBCheckbox() {
        new ItemsPage().getCheckBox3GB().click();
    }


}
