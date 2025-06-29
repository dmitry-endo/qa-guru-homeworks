package lesson_7;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import tests.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static utils.RandomUtils.*;

public class PracticeFormWithRandomDataGenerationTest extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String email = getRandomEmail();
    String gender = getRandomGender();
    String userNumber = getRandomMobile();
    String day = getRandomBirthDay();
    String month = getRandomBirthMonth();
    String year = getRandomBirthYear();
    String subject = getRandomSubject();
    String hobby = getRandomHobby();
    String picture = getRandomPicture();
    String currentAddress = getRandomStreetAddress();
    String state = getRandomState();
    String city = getRandomCity(state);

    String fullName = firstName + " " + lastName;
    String fullDate = String.format("%s %s,%s", day, month, year);
    String stateAndCity = state + " " + city;

    @Test
    void successfulPracticeFormTest() {

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();

        practiceFormPage.checkResultTableHasAppeared()
                .checkResultTableRecord("Student Name", fullName)
                .checkResultTableRecord("Student Email", email)
                .checkResultTableRecord("Gender", gender)
                .checkResultTableRecord("Mobile", userNumber)
                .checkResultTableRecord("Date of Birth", fullDate)
                .checkResultTableRecord("Subjects", subject)
                .checkResultTableRecord("Hobbies", hobby)
                .checkResultTableRecord("Picture", picture)
                .checkResultTableRecord("Address", currentAddress)
                .checkResultTableRecord("State and City", stateAndCity)
                .clickCloseModal();
    }

    @Test
    void minimalPracticeFormTest() {

        // Setting up today's date for test cuz the form always has it set
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);
        String formattedDate = today.format(formatter);

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .clickSubmit();

        practiceFormPage.checkResultTableHasAppeared()
                .checkResultTableRecord("Student Name", fullName)
                .checkResultTableRecord("Gender", gender)
                .checkResultTableRecord("Mobile", userNumber)
                .checkResultTableRecord("Date of Birth", formattedDate)
                .checkResultTableRecordIsEmpty("Student Email")
                .checkResultTableRecordIsEmpty("Subjects")
                .checkResultTableRecordIsEmpty("Hobbies")
                .checkResultTableRecordIsEmpty("Picture")
                .checkResultTableRecordIsEmpty("Address")
                .checkResultTableRecordIsEmpty("State and City")
                .clickCloseModal();
    }

    @Test
    void negativePracticeFormTest() {

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setEmail(email)
                .setUserNumber(userNumber)
                .uploadPicture(picture)
                .clickSubmit();

        practiceFormPage.checkResultTableHasNotAppeared();
    }
}