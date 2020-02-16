package pages;

import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static steps.Base.getDriver;

@Data
public class BasePage {

    public BasePage(){
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='search']")
    public WebElement searchInput;

    @FindBy(xpath = "//a[@class='b1l3 suggestions-item type-suggests']")
    public WebElement iphoneInSearchFieldList;

    public static void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
