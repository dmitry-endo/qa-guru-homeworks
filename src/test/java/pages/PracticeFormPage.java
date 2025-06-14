package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.HashTableComponent;
import utils.JavaScriptActions;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement practiceFormTitle = $(".text-center");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement selectState = $("#state");
    private final SelenideElement selectCity = $("#city");
    private final SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    private final SelenideElement modalWindow = $(".modal-dialog");
    private final SelenideElement modalWindowTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement closeModalButton = $("#closeLargeModal");

    CalendarComponent calendarComponent = new CalendarComponent();
    JavaScriptActions javaScriptActions = new JavaScriptActions();
    HashTableComponent hashTableComponent = new HashTableComponent();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        practiceFormTitle.shouldHave(text("Practice Form"));
        javaScriptActions.removeFixedBanners();

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public PracticeFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    public PracticeFormPage setAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public PracticeFormPage setState(String value) {
        selectState.scrollIntoView(true).click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage setCity(String value) {
        selectCity.scrollIntoView(true).click();
        stateCityWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormPage checkResultTableHasAppeared() {
        modalWindow.should(appear);
        modalWindowTitle.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public PracticeFormPage checkResultTableRecord(String key, String value) {
        hashTableComponent.positiveCheck(key, value);

        return this;
    }

    public PracticeFormPage checkResultTableRecordIsEmpty(String key, String value) {
        hashTableComponent.negativeCheck(key, value);

        return this;
    }

    public void checkResultTableHasNotAppeared() {
        modalWindow.shouldNot(appear);
    }

    public void clickSubmit() {
        submitButton.scrollIntoView(true).click();
    }

    public void clickCloseModal() {
        closeModalButton.scrollIntoView(true).click();
    }
}