package steps;

import cucumber.api.java.ru.Когда;

public class CucumberSteps {

    @Когда ("Перейдите на сервис \"(.+)\"$")
    public void goTo(){

    }
    @Когда ("Выполните поиск по \"(.+)\"$")
    public void search(){

    }
    @Когда ("Ограничить цену до \"(.+)\"$")
    public void limitPrice(){

    }
    @Когда ("Отметить чекбокс – \"(.+)\"$")
    public void chooseHighRate(){

    }
    @Когда ("Отметить чекбокс – \"(.+)\"$")
    public void chooseGB(){

    }
    @Когда ("Из результатов поиска добавьте в корзину первые 8 нечетных товаров.")
    public void addEightItems(){

    }
    @Когда ("Запомнить название товаров")
    public void rememberItemsNames(){

    }
    @Когда ("Перейдите в корзину, убедитесь, что все добавленные ранее товары находятся в корзине")
    public void goToBasket(){

    }
    @Когда ("Проверить, что отображается текст \"(.+)\"$")
    public void checkBasket(){

    }
    @Когда ("Удалите все товары из корзины")
    public void deleteAllItems(){

    }
    @Когда ("Проверьте, что корзина не содержит никаких товаров")
    public void isBasketEmpty(){

    }
}
