package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static steps.Base.getDriver;

@Data
public class BasePage {

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='search']")
    public WebElement searchInput;

    @FindBy(xpath = "//button[@qa-id='header-search-go']")
    public WebElement searchButton;

    WebDriverWait wait = new WebDriverWait(getDriver(), 30);

    @FindBy(xpath = "//span[@class='exponea-close-cross']")
    public WebElement cross;

    public void goToMainPage() {
//        if(cross.isDisplayed()){
//            cross.click();
//    }
    }

    public static void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//        wait.until(ExpectedConditions.visibilityOf(element));
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
