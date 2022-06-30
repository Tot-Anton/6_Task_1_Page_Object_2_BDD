package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private static final SelenideElement cardSelector1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static final SelenideElement cardSelector2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement buttonSelector = $("button");
    private static final ElementsCollection cardsSelector = $$(".list__item ");
    private final SelenideElement updateSelector = $("[data-test-id='action-reload']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    //выбор карты для перевода
    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cardsSelector.findBy(text(cardInfo.getCardNumber().substring(12, 16))).$("button").click();
        return new TransferPage();
    }

    //получаем баланс карты
    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cardsSelector.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }

    //извлекаем баланс
    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }


}
