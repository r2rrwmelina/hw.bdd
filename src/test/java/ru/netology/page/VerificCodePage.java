package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificCodePage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement buttonField = $("button");

    public VerificCodePage() {
        codeField.should(Condition.visible);
    }

    public UserCardsPage validCode(DataHelper.VerifCode code) {
        codeField.setValue(code.getCode());
        buttonField.click();
        return new UserCardsPage();
    }
}