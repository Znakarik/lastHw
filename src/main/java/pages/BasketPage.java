package pages;

import lombok.Data;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseCucu;

import static steps.BaseCucu.getDriver;

@Data
public class BasketPage {

    public BasketPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Ваша корзина')]")
    WebElement yourBasket;

    @FindBy(xpath = "//span[contains(text(),'товаров')]")
    WebElement itemsCount;

//    String newYourBasket = yourBasket.getText().trim();
//    String newItemsCount = itemsCount.getText().trim().substring(0,8);

}
