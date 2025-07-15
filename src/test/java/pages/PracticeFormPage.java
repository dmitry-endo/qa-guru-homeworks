package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Открываем страницу (плюс удаляем баннеры)")
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        practiceFormTitle.shouldHave(text("Practice Form"));
        javaScriptActions.removeFixedBanners();

        return this;
    }

    @Step("Заполняем поле First Name: {firstName}")
    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    @Step("Заполняем поле Last Name: {lastName}")
    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Заполняем поле Email: {email}")
    public PracticeFormPage setEmail(String email) {
        userEmailInput.setValue(email);

        return this;
    }

    @Step("Выбираем радиокнопку Gender: {gender}")
    public PracticeFormPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();

        return this;
    }

    @Step("Заполняем поле Mobile: {mobile}")
    public PracticeFormPage setUserNumber(String mobile) {
        userNumberInput.setValue(mobile);

        return this;
    }

    @Step("Заполняем поле Date of Birth: {day} {month} {year}")
    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Заполняем поле Subjects: {subject}")
    public PracticeFormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    @Step("Выбираем чек-бокс Hobbies: {hobby}")
    public PracticeFormPage setHobby(String hobby) {
        hobbiesWrapper.$(byText(hobby)).click();

        return this;
    }

    @Step("Загружаем картинку: {picture}")
    public PracticeFormPage uploadPicture(String picture) {
        uploadPicture.uploadFromClasspath(picture);

        return this;
    }

    @Step("Заполняем поле Current Address: {currentAddress}")
    public PracticeFormPage setAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Выбираем State в выпадающем списке: {state}")
    public PracticeFormPage setState(String state) {
        selectState.scrollIntoView(true).click();
        stateCityWrapper.$(byText(state)).click();

        return this;
    }

    @Step("Выбираем City в выпадающем списке: {city}")
    public PracticeFormPage setCity(String city) {
        selectCity.scrollIntoView(true).click();
        stateCityWrapper.$(byText(city)).click();

        return this;
    }

    @Step("Проверяем, что появилось модальное окно с результатами и содержит соответствующий текст")
    public PracticeFormPage checkResultTableHasAppeared() {
        modalWindow.should(appear);
        modalWindowTitle.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    @Step("Проверяем, что в таблице для ключа {key} соответствует значение {value}")
    public PracticeFormPage checkResultTableRecord(String key, String value) {
        hashTableComponent.positiveCheck(key, value);

        return this;
    }

    @Step("Проверяем, что в таблице для ключа {key} отсутствует значение")
    public PracticeFormPage checkResultTableRecordIsEmpty(String key) {
        hashTableComponent.negativeCheck(key);

        return this;
    }

    @Step("Проверяем, что модальное окно с результатами не появилось")
    public void checkResultTableHasNotAppeared() {
        modalWindow.shouldNot(appear);
    }

    @Step("Нажимаем кнопку Submit")
    public void clickSubmit() {
        submitButton.scrollIntoView(true).click();
    }

    @Step("Нажимаем кнопку Close")
    public void clickCloseModal() {
        closeModalButton.scrollIntoView(true).click();
    }
}