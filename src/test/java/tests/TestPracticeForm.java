package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillPracticeFormTest() {
        // Setting up variables for tests flexibility
        String firstName = "Dmitry";
        String lastName = "Endo";
        String email = "dmitry@bk.com";
        String gender = "Male";
        String number = "9998554545";
        Dictionary<String, String> dateOfBirth = new Hashtable<>();
        dateOfBirth.put("day", "1");
        dateOfBirth.put("month", "January");
        dateOfBirth.put("year", "1980");
        String[] subjects = {"Maths", "Arts", "English"};
        String[] hobbies = {"Sports", "Music"};
        String uploadFileName = "avatar.jpg";
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
        $(".react-datepicker__month-select")
                .$(byText(dateOfBirth.get("month"))).click();
        $(".react-datepicker__year-select")
                .$(byText(dateOfBirth.get("year"))).click();
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(dateOfBirth.get("day")))
                .shouldBe(visible)
                .click();

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

        // Choosing state
        $("#state").scrollIntoView(true).click();
        $$("div[id^='react-select'][class*='option']").findBy(text(state)).click();

        // Choosing city
        $("#city").scrollIntoView(true).click();
        $$("div[id^='react-select'][class*='option']").findBy(text(city)).click();

        // Clicking 'Submit' button
        $("#submit").click();

        // Checking modal window to be open
        $(".modal-content").shouldBe(visible);

        // Variables for combined fields
        String expectedSubject = String.join(", ", subjects);
        String expectedHobbies = String.join(", ", hobbies);
        String fullDate = dateOfBirth.get("day") + " "
                + dateOfBirth.get("month") + ","
                + dateOfBirth.get("year");

        // Checking results in modal window after submitting
        $$("table tbody tr").findBy(text("Student Name"))
                .shouldHave(text(firstName + " " + lastName));
        $$("table tbody tr").findBy(text("Student Email")).shouldHave(text(email));
        $$("table tbody tr").findBy(text("Gender")).shouldHave(text(gender));
        $$("table tbody tr").findBy(text("Mobile")).shouldHave(text(number));
        $$("table tbody tr").findBy(text("Date of Birth")).shouldHave(text(fullDate));
        $$("table tbody tr").findBy(text("Subjects")).shouldHave(text(expectedSubject));
        $$("table tbody tr").findBy(text("Hobbies")).shouldHave(text(expectedHobbies));
        $$("table tbody tr").findBy(text("Picture")).shouldHave(text(uploadFileName));
        $$("table tbody tr").findBy(text("Address")).shouldHave(text(currentAddress));
        $$("table tbody tr").findBy(text("State and City"))
                .shouldHave(text(state + " " + city));

        // Clicking 'Close' button for modal window
        $("#closeLargeModal").click();
    }
}
