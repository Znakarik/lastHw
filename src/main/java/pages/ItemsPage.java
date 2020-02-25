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

    @FindBy(xpath = "//div[contains(text(), 'Цена')]/..//input[contains(@id,'to')]")
    private WebElement limitInput;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement priorityCheckbox;

    @FindBy(xpath = "//span[contains(text(),'3 ГБ')]/../../div[@class='ui-at4']")
    private WebElement checkBox3GB;

    @FindBy(xpath = "//a[@class='b5g9 suggestions-item type-suggests']")
    private WebElement firstItemFromSearchList;

    @FindBy(xpath = "//div[@class='widget-search-result-container ar9']//div[@class='a4b4']")
    private List<WebElement> itemsList;

    /** Brands elements */
    @FindBy(xpath = "(//span[contains(text(),'Посмотреть все')])[1]")
    private WebElement showAllButton;
    @FindBy(xpath = "//p[@class='ui-a0l4']/preceding-sibling::input")
    private WebElement brandInput;
    @FindBy(xpath = "(//span[contains(text(),'Beats')])")
    private WebElement beats;
    @FindBy(xpath = "(//span[contains(text(),'Samsung')])")
    private WebElement samsung;

    @FindBy(xpath = "(//div[@data-widget='searchResultsV2']//a[@data-test-id='tile-name'])")
    private List <WebElement> brandsTitles;

}
