package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseCucu;

import java.util.List;

@Data
public class ItemsPage extends BasePage {

    public ItemsPage() {
        PageFactory.initElements(BaseCucu.getDriver(), this);
    }

    @FindBy(xpath = "(//div[contains(@class,'ui-a0l0 ui-a0g5')]//input)[2]")
    public WebElement limitInput;

    @FindBy(xpath = "//input[@role='combobox']")
    public WebElement priorityCheckbox;

    @FindBy(xpath = "//span[contains(text(),'3 ГБ')]/../../div[@class='ui-at4']")
    public WebElement checkBox3GB;

    @FindBy(xpath = "//a[@class='b5g9 suggestions-item type-suggests']")
    public WebElement firstItemFromSearchList;

    @FindBy(xpath = "//div[@class='widget-search-result-container ar9']//div[@class='a4b4']")
    public List<WebElement> itemsList;
}
