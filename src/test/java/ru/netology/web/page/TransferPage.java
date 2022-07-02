package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $(byText("На вашем счете недостаточно средств"));

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    //должно появиться сообщение ошибки
    public void anErrorMessageShouldAppear() {
        errorMessage.shouldBe(visible);
    }

    //Делаем перевод
    public DashboardPage makeTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

}
