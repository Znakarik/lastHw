package steps;

import cucumber.api.java.ru.Когда;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;
import pages.ItemsPage;

public class CucumberSteps {
    ItemsPage itemsPage;

    @Когда("Перейдите на сервис http://www.ozon.ru/")
    public void goTo() throws InterruptedException {
        itemsPage = new ItemsPage();
        new BasePage().goToMainPage();
        System.out.println("Вы на главной странице");
        Thread.sleep(3000);
    }

    @Когда("Выполните поиск по \"(.+)\"$")
    public void search(String input) throws InterruptedException {
        Thread.sleep(3000);
        new ItemsPageSteps().searchIphone(input);
        Thread.sleep(3000);
        System.out.println("Вы выполнили поиск " + input);
        Thread.sleep(3000);
    }

    @Когда("Ограничить цену до \"(.+)\"$")
    public void limitPrice(String limit) throws InterruptedException {
        new ItemsPageSteps().limitPrice(limit);
        System.out.println("Вы ограничили цену до " + limit);
    }

    @Когда("Отметить чекбокс – \"(.+)\"$")
    public void chooseHighRate(String priority) {
        new ItemsPageSteps().voteHighPriority(priority);
    }

    @Когда("Отметить чекбокс – \"3Гб\"")
    public void chooseGB() {
        new ItemsPageSteps().vote3GBCheckbox();
    }

    @Когда("Из результатов поиска добавьте в корзину первые 8 нечетных товаров.")
    public void addEightItems() {

    }

    @Когда("Запомнить название товаров")
    public void rememberItemsNames() {

    }

    @Когда("Перейдите в корзину, убедитесь, что все добавленные ранее товары находятся в корзине")
    public void goToBasket() {

    }

    @Когда("Проверить, что отображается текст \"(.+)\"$")
    public void checkBasket() {

    }

    @Когда("Удалите все товары из корзины")
    public void deleteAllItems() {

    }

    @Когда("Проверьте, что корзина не содержит никаких товаров")
    public void isBasketEmpty() {

    }
}
