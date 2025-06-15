package pages;

import com.codeborne.selenide.SelenideElement;
import utils.JavaScriptActions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    private final SelenideElement textBoxTitle = $(".text-center");
    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement outputWrapper = $("#output");
    private final SelenideElement submitButton = $("#submit");

    JavaScriptActions javaScriptActions = new JavaScriptActions();

    public TextBoxPage openPage() {
        open("/text-box");
        textBoxTitle.shouldHave(text("Text Box"));
        javaScriptActions.removeFixedBanners();

        return this;
    }

    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);

        return this;
    }

    public TextBoxPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public void clickSubmit() {
        submitButton.scrollIntoView(true).click();
    }

    public TextBoxPage checkResult(String value) {
        outputWrapper.shouldHave(text(value));

        return this;
    }
}
