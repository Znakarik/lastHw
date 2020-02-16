package steps;

import pages.BasePage;
import pages.ItemsPage;

import static pages.BasePage.click;

public class ItemsPageSteps {
    BasePage basePage = new BasePage();
    ItemsPage itemsPage = new ItemsPage();

    public void searchIphone(String input) {
        basePage.getSearchInput().click();
        basePage.getSearchInput().sendKeys(input);
        click(basePage.getIphoneInSearchFieldList());
    }

    public void voteHighPriority(String priority){
        itemsPage.getPriorityCheckbox().sendKeys(priority);
    }

    public void vote3GBCheckbox(){
        itemsPage.getCheckBox3GB().click();
    }


}
