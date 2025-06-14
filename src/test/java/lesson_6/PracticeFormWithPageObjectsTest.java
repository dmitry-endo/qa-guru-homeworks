package lesson_6;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import tests.TestBase;

public class PracticeFormWithPageObjectsTest extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    String firstName = "Dmitry";
    String lastName = "Endo";
    String email = "dmitry@bk.com";
    String gender = "Male";
    String userNumber = "9998554545";
    String day = "30";
    String month = "January";
    String year = "1980";
    String[] subjects = {"Maths", "Arts", "English"};
    String[] hobbies = {"Sports", "Music"};
    String uploadFileName = "avatar.jpg";
    String currentAddress = "Arbat street";
    String state = "NCR";
    String city = "Noida";
    String fullName = firstName + " " + lastName;
    String expectedSubject = String.join(", ", subjects);
    String expectedHobbies = String.join(", ", hobbies);
    String fullDate = day + " " + month + "," + year;
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
                .uploadPicture(uploadFileName)
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
                .checkResultTableRecord("Picture", uploadFileName)
                .checkResultTableRecord("Address", currentAddress)
                .checkResultTableRecord("State and City", stateAndCity)
                .clickCloseModal();
    }

    @Test
    void minimalPracticeFormTest() {

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
                .checkResultTableRecordIsEmpty("Student Email", email)
                .checkResultTableRecordIsEmpty("Date of Birth", fullDate)
                .checkResultTableRecordIsEmpty("Subjects", expectedSubject)
                .checkResultTableRecordIsEmpty("Hobbies", expectedHobbies)
                .checkResultTableRecordIsEmpty("Picture", uploadFileName)
                .checkResultTableRecordIsEmpty("Address", currentAddress)
                .checkResultTableRecordIsEmpty("State and City", stateAndCity)
                .clickCloseModal();
    }

    @Test
    void negativePracticeFormTest() {

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setEmail(email)
                .setUserNumber(userNumber)
                .uploadPicture(uploadFileName)
                .clickSubmit();

        practiceFormPage.checkResultTableHasNotAppeared();
    }
}