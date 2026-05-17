package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldToTransferMoneyBetweenCards() {
        var info = DataHelper.getAuthInfo();
        var code = DataHelper.getVerifCode();
        var card1 = DataHelper.getFirstCardInfo();
        var card2 = DataHelper.getSecondCardInfo();

        var loginPage = new LoginPage();
        var verificCodePage = loginPage.validLogin(info);
        var userCardPage = verificCodePage.validCode(code);

        var balanceCard1Before = userCardPage.getCardBalance(card1);
        var balanceCard2Before = userCardPage.getCardBalance(card2);

        var transferPage = userCardPage.chooseCard(card2);
        var amount = balanceCard2Before / 10;
        var userCardsPage = transferPage.successTransaction(card1, amount);

        var balanceCard1After = userCardPage.getCardBalance(card1);
        var balanceCard2After = userCardPage.getCardBalance(card2);

        Assertions.assertEquals(balanceCard2Before + amount, balanceCard2After);
        Assertions.assertEquals(balanceCard1Before - amount, balanceCard1After);
    }

    @Test
    void shouldCancelTransferBetweenCards() {
        var info = DataHelper.getAuthInfo();
        var code = DataHelper.getVerifCode();
        var card1 = DataHelper.getFirstCardInfo();
        var card2 = DataHelper.getSecondCardInfo();

        var loginPage = new LoginPage();
        var verificCodePage = loginPage.validLogin(info);
        var userCardPage = verificCodePage.validCode(code);

        var balanceCard1Before = userCardPage.getCardBalance(card1);
        var balanceCard2Before = userCardPage.getCardBalance(card2);

        var transferPage = userCardPage.chooseCard(card2);
        var amount = balanceCard2Before / 10;
        var userCardsPage = transferPage.cancelTransaction(card1, amount);

        var balanceCard1After = userCardPage.getCardBalance(card1);
        var balanceCard2After = userCardPage.getCardBalance(card2);

        Assertions.assertEquals(balanceCard2Before, balanceCard2After);
        Assertions.assertEquals(balanceCard1Before, balanceCard1After);
    }

    @Test
    void shouldNotToTransferMoneyWithWrongCardInfo() {
        var info = DataHelper.getAuthInfo();
        var code = DataHelper.getVerifCode();
        var wrongCard1 = DataHelper.getWrongCardInfo();
        var card2 = DataHelper.getSecondCardInfo();

        var loginPage = new LoginPage();
        var verificCodePage = loginPage.validLogin(info);
        var userCardPage = verificCodePage.validCode(code);

        var balanceCard2Before = userCardPage.getCardBalance(card2);
        var transferPage = userCardPage.chooseCard(card2);
        var amount = balanceCard2Before / 10;
        var message = "Ошибка! Произошла ошибка";
        var userCardsPage = transferPage.errorTransaction(wrongCard1, amount, message);
    }

//    @Test
//    void shouldNotToTransferMoneyBetweenCards() {
//        var info = DataHelper.getAuthInfo();
//        var code = DataHelper.getVerifCode();
//        var card1 = DataHelper.getFirstCardInfo();
//        var card2 = DataHelper.getSecondCardInfo();
//
//        var loginPage = new LoginPage();
//        var verificCodePage = loginPage.validLogin(info);
//        var userCardPage = verificCodePage.validCode(code);
//
//        var balanceCard1Before = userCardPage.getCardBalance(card1);
//        var balanceCard2Before = userCardPage.getCardBalance(card2);
//
//        var transferPage = userCardPage.chooseCard(card2);
//        var amount = balanceCard2Before * 10; // проверка, что не переведется сумма больше, чем есть на карте
//        var userCardsPage = transferPage.errorTransaction(card1, amount);
//    }
}