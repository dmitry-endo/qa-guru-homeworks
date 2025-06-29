package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillPracticeFormTest() {
        // Setting up variables for tests flexibility
        String firstName = "Dmitry";
        String lastName = "Endo";
        String email = "dmitry@bk.com";
        String gender = "Male";
        String number = "9998554545";
        String day = "30";
        String month = "January";
        String year = "1980";
        String[] subjects = {"Maths", "Arts", "English"};
        String[] hobbies = {"Sports", "Music"};
        String uploadFileName = "avatar1.jpg";
        String currentAddress = "Arbat street";
        String state = "NCR";
        String city = "Noida";

        // Relative path for baseUrl
        open("/automation-practice-form");

        // Setting up basic info
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(number);

        // Choosing 'Date of Birth' field by calendar widget
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(day)).click();

        // Filling out 'Subjects' field
        $("#subjectsInput").setValue(subjects[0]).pressEnter();
        $("#subjectsInput").setValue(subjects[1]).pressEnter();
        $("#subjectsInput").setValue(subjects[2]).pressEnter();

        // Choosing checkboxes for 'Hobbies'
        $("#hobbiesWrapper").$(byText(hobbies[0])).click();
        $("#hobbiesWrapper").$(byText(hobbies[1])).click();

        // File uploading for 'Picture'
        $("#uploadPicture").uploadFromClasspath(uploadFileName);

        // Filling out 'Current Address' field
        $("#currentAddress").setValue(currentAddress);

        // Choosing state and city
        $("#state").scrollIntoView(true).click();
        $$("div[id^='react-select'][class*='option']").findBy(text(state)).click();
        $("#city").click();
        $$("div[id^='react-select'][class*='option']").findBy(text(city)).click();

        // Clicking 'Submit' button
        $("#submit").click();

        // Checking modal window to be open
        $(".modal-content").shouldBe(visible);

        // Variables for combined fields
        String fullName = firstName + " " + lastName;
        String expectedSubject = String.join(", ", subjects);
        String expectedHobbies = String.join(", ", hobbies);
        String fullDate = day + " " + month + "," + year;
        String stateAndCity = state + " " + city;

        // Checking results in modal window after submitting
        // This ensures that each row contains corresponding values
        // e.g. Student Name row should have full name next to it
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(fullName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(number));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(fullDate));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(expectedSubject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(expectedHobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(uploadFileName));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(stateAndCity));

        // Clicking 'Close' button for modal window
        $("#closeLargeModal").click();
    }
}