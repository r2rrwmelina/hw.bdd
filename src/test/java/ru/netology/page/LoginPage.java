package ru.netology.page;

import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public VerificCodePage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id='login'] input").setValue(info.getLogin());
        $("[data-test-id='password'] input").setValue(info.getPassword());
        $("button").click();
        return new VerificCodePage();
    }
}