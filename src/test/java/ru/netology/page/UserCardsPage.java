package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserCardsPage {
    private final SelenideElement title = $("[data-test-id='dashboard']");
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceEnd = " р.";

    public UserCardsPage() {
        title.should(Condition.visible).should(Condition.text("Личный кабинет"));
    }

    private SelenideElement findCard(DataHelper.Card card) {
        return cards.find(Condition.attribute("data-test-id", card.getTestId()));
    }

    public int getCardBalance(DataHelper.Card card) {
        String text = findCard(card).getText();
        return extractBalance(text);
    }

    public TransferPage chooseCard(DataHelper.Card card) {
        findCard(card).$("button").click();
        return new TransferPage();
    }

    public int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var end = text.indexOf(balanceEnd);
        var value = text.substring(start + balanceStart.length(), end);
        return Integer.parseInt(value);
    }
}