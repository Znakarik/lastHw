package pages;

import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.Base;

@Data
public class ItemsPage extends BasePage {

    public ItemsPage() {
        PageFactory.initElements(Base.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='to_1942']")
    public WebElement limitInput;

    @FindBy(xpath = "//input[@role='combobox']")
    public WebElement priorityCheckbox;

    @FindBy(xpath = "//span[contains(text(),'3 ГБ')]/../../div[@class='ui-at4']")
    public WebElement checkBox3GB;
}
