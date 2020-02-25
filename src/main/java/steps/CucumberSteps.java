package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import pages.Product;

import java.io.IOException;

public class CucumberSteps {

    @Когда("Перейдите на сервис \"(.+)\"$")
    public void goTo(String site) throws InterruptedException {
        new ItemsPageSteps().goTo(site);
        System.out.println("Вы на главной странице");
        System.out.println(ItemsPageSteps.class.getClassLoader());
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

    @Когда("Бренды : \"(.+)\", \"(.+)\"")
    public void chooseBrands(String first, String second) throws InterruptedException {
        if (BaseCucu.isSecond()) {
            new ItemsPageSteps().chooseBrands(first,second);
        }
    }



    @Когда("Отметить чекбокс – \"(.+)\"$")
    public void chooseHighRate(String priority) {
        new ItemsPageSteps().voteHighPriority(priority);
        System.out.println("Вы выбрали приоритет \"" + priority + "\"");
    }

    @Когда("Отметить чекбокс –  \"(.+)\"$")
    public void chooseGB(String amount) {
        new ItemsPageSteps().voteCheckbox(amount);
        System.out.println("Вы выбрали обьем \"" + amount + "\"");
    }

    @Когда("Из результатов поиска добавьте в корзину все четные товары")
    public void add() throws InterruptedException {
            addItems();
    }

    @Когда("Из результатов поиска добавьте в корзину первые 8 нечетных  товаров")
    public void addItems() throws InterruptedException {
        new ItemsPageSteps().addItemsToCard();
        Product.productList.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice()));
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

    @Тогда("Приложить файл с информацией о всех добавленных товарах, указать товар с наибольшей ценой")
    public void generateProductInfo() throws IOException {
    new BasketPageSteps().generateInfo();
    }
}
