package steps;

import cucumber.api.java.ru.Когда;
import pages.ItemsPage;

public class CucumberSteps {
    ItemsPage itemsPage;

    @Когда("Перейдите на сервис http://www.ozon.ru/")
    public void goTo() {
        itemsPage = new ItemsPage();
    }

    @Когда("Выполните поиск по \"(.+)\"$")
    public void search(String input) {
        new ItemsPageSteps().searchIphone(input);

    }

    @Когда("Ограничить цену до \"(.+)\"$")
    public void limitPrice(String limit) {
        new ItemsPage().getLimitInput().sendKeys(limit);
    }

    @Когда("Отметить чекбокс – \"(.+)\"$")
    public void chooseHighRate(String priority) {
        new ItemsPageSteps().voteHighPriority(priority);
    }

        @Когда ("Отметить чекбокс – \"3Гб\"")
    public void chooseGB(){
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
