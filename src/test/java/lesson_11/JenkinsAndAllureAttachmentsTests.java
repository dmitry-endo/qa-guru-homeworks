package lesson_11;

import data.PracticeFormUser;
import org.junit.jupiter.api.*;
import pages.PracticeFormPage;
import tests.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты на заполнение формы demoqa - Practice Form")
@Tag("demoqa_lesson11")
public class JenkinsAndAllureAttachmentsTests extends TestBase {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();
    private final PracticeFormUser user = new PracticeFormUser();

    @Test
    @DisplayName("Тест на успешное заполнение формы")
    void successfulPracticeFormTest() {

        step("Заполняем Practice Form и жмем submit", () ->
                practiceFormPage.openPage()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setEmail(user.getEmail())
                    .setGender(user.getGender())
                    .setUserNumber(user.getUserNumber())
                    .setDateOfBirth(user.getDay(), user.getMonth(), user.getYear())
                    .setSubject(user.getSubject())
                    .setHobby(user.getHobby())
                    .uploadPicture(user.getPicture())
                    .setAddress(user.getCurrentAddress())
                    .setState(user.getState())
                    .setCity(user.getCity())
                    .clickSubmit());

        step("Проверяем результаты в модальном окне и закрываем его", () ->
                practiceFormPage.checkResultTableHasAppeared()
                    .checkResultTableRecord("Student Name", user.getFullName())
                    .checkResultTableRecord("Student Email", user.getEmail())
                    .checkResultTableRecord("Gender", user.getGender())
                    .checkResultTableRecord("Mobile", user.getUserNumber())
                    .checkResultTableRecord("Date of Birth", user.getFullBirthDate())
                    .checkResultTableRecord("Subjects", user.getSubject())
                    .checkResultTableRecord("Hobbies", user.getHobby())
                    .checkResultTableRecord("Picture", user.getPicture())
                    .checkResultTableRecord("Address", user.getCurrentAddress())
                    .checkResultTableRecord("State and City", user.getStateAndCity())
                    .clickCloseModal());
    }

    @Test
    @DisplayName("Тест на минимальное заполнение формы")
    void minimalPracticeFormTest() {

        // Setting up today's date for test cuz the form always has it set
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);
        String formattedDate = today.format(formatter);

        step("Заполняем обязательные поля Practice Form и жмем submit", () ->
                practiceFormPage.openPage()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setGender(user.getGender())
                    .setUserNumber(user.getUserNumber())
                    .clickSubmit());

        step("Проверяем результаты в модальном окне и закрываем его", () ->
                practiceFormPage.checkResultTableHasAppeared()
                    .checkResultTableRecord("Student Name", user.getFullName())
                    .checkResultTableRecord("Gender", user.getGender())
                    .checkResultTableRecord("Mobile", user.getUserNumber())
                    .checkResultTableRecord("Date of Birth", formattedDate)
                    .checkResultTableRecordIsEmpty("Student Email")
                    .checkResultTableRecordIsEmpty("Subjects")
                    .checkResultTableRecordIsEmpty("Hobbies")
                    .checkResultTableRecordIsEmpty("Picture")
                    .checkResultTableRecordIsEmpty("Address")
                    .checkResultTableRecordIsEmpty("State and City")
                    .clickCloseModal());
    }

    @Test
    @DisplayName("Негативный тест на заполнение формы")
    void negativePracticeFormTest() {

        step("Заполняем не все обязательные поля Practice Form и жмем submit", () ->
                practiceFormPage.openPage()
                    .setFirstName(user.getFirstName())
                    .setEmail(user.getEmail())
                    .setUserNumber(user.getUserNumber())
                    .uploadPicture(user.getPicture())
                    .clickSubmit());

        step("Проверяем, что модальное окно с результатами не появилось",
                practiceFormPage::checkResultTableHasNotAppeared);
    }

    @Disabled
    @Test
    @DisplayName("Отключенный тест на заполнение формы")
    void disabledPracticeFormTest() {
        /// some code here
    }
}
