package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement subtitle = $("h1");
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement from = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage() {
        subtitle.should(Condition.visible).should(Condition.text("Пополнение карты"));
    }

    public UserCardsPage successTransaction(DataHelper.Card card, int amount) {
        amountField.setValue(String.valueOf(amount));
        from.setValue(card.getNumber());
        transferButton.click();
        return new UserCardsPage();
    }

    public UserCardsPage cancelTransaction(DataHelper.Card card, int amount) {
        amountField.setValue(String.valueOf(amount));
        from.setValue(card.getNumber());
        cancelButton.click();
        return new UserCardsPage();
    }

    public TransferPage errorTransaction(DataHelper.Card card, int amount) {
        amountField.setValue(String.valueOf(amount));
        from.setValue(card.getNumber());
        transferButton.click();
        errorMessage.should(Condition.visible).should(Condition.text("Ошибка"));
        return this;
    }
}