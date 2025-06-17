package lesson_7;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import tests.TestBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static utils.RandomUtils.*;

public class PracticeFormWithRandomDataGenerationTest extends TestBase {

    private static final int NUM_OF_SUBJECTS = 3;
    private static final int NUM_OF_HOBBIES = 2;
    private static final String UPLOAD_FILE_NAME = "avatar.jpg";

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String email = getRandomEmail();
    String gender = getRandomGender();
    String userNumber = getRandomMobile();
    String day = getRandomBirthDay();
    String month = getRandomBirthMonth();
    String year = getRandomBirthYear();
    String[] subjects = getRandomSubjects(NUM_OF_SUBJECTS);
    String[] hobbies = getRandomHobbies(NUM_OF_HOBBIES);
    String currentAddress = getRandomStreetAddress();
    String state = getRandomState();
    String city = getRandomCity(state);

    String fullName = firstName + " " + lastName;
    String expectedSubject = String.join(", ", subjects);
    String expectedHobbies = String.join(", ", hobbies);
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
                .setSubject(subjects[0])
                .setSubject(subjects[1])
                .setSubject(subjects[2])
                .setHobby(hobbies[0])
                .setHobby(hobbies[1])
                .uploadPicture(UPLOAD_FILE_NAME)
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
                .checkResultTableRecord("Subjects", expectedSubject)
                .checkResultTableRecord("Hobbies", expectedHobbies)
                .checkResultTableRecord("Picture", UPLOAD_FILE_NAME)
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
                .uploadPicture(UPLOAD_FILE_NAME)
                .clickSubmit();

        practiceFormPage.checkResultTableHasNotAppeared();
    }
}