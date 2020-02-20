package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pages.BasePage;
import pages.ItemsPage;
import pages.Product;

public class CucumberSteps {
    ItemsPage itemsPage;

    @Когда("Перейдите на сервис http://www.ozon.ru/")
    public void goTo() throws InterruptedException {
        itemsPage = new ItemsPage();
        new BasePage().goToMainPage();
        System.out.println("Вы на главной странице");
    }

    @Когда("Выполните поиск по \"(.+)\"$")
    public void search(String input) throws InterruptedException {
        new ItemsPageSteps().searchIphone(input);
        System.out.println("Вы выполнили поиск " + input);
    }

    @Когда("Ограничить цену до \"(.+)\"$")
    public void limitPrice(String limit) throws InterruptedException {
        new ItemsPageSteps().limitPrice(limit);
        System.out.println("Вы ограничили цену до " + limit);
    }

    @Когда("Отметить чекбокс – \"(.+)\"$")
    public void chooseHighRate(String priority) {
        new ItemsPageSteps().voteHighPriority(priority);
        System.out.println("Вы выбрали приоритет \"" + priority + "\"");
    }

    @Когда("Отметить чекбокс –  \"(.+)\"$")
    public void chooseGB(String amount) {
//        try {
        new ItemsPageSteps().voteCheckbox(amount);
//        } catch (NoSuchElementException e){
//            e.getStackTrace();
//        }
        System.out.println("Вы выбрали обьем \"" + amount + "\"");
    }

    @Когда("Из результатов поиска добавьте в корзину первые 8 нечетных  товаров.")
    public void addEightItems() throws InterruptedException {
        new ItemsPageSteps().addItemsToCard();
        Product.productList.forEach(product -> System.out.println(product.getName()));
    }

    @Когда("Запомнить название товаров")
    public void rememberItemsNames() {
        new ItemsPageSteps().rememberItemsNames();
    }

    @Когда("Перейдите в корзину, убедитесь, что все добавленные ранее товары находятся в корзине")
    public void goToBasket() throws InterruptedException {
        new ItemsPageSteps().goToBasketPage();
        new BasketPageSteps().checkIsBasketContainsItems();
    }

    @Когда("Проверить, что отображается текст \"(.+)\"$")
    public void checkBasket(String text) {
        try {
            new BasketPageSteps().checkIsTextExist(text);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Когда("Удалите все товары из корзины")
    public void deleteAllItems() throws InterruptedException {
        new BasketPageSteps().deleteAll();
        Thread.sleep(5000);
    }

    @Тогда("Проверьте, что корзина не содержит никаких товаров")
    public void isBasketEmpty() throws InterruptedException {
        new BasketPageSteps().checkBasketIsEmpty();
    }
}
