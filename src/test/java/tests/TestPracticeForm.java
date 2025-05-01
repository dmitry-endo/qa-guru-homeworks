package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillPracticeForm() {
        // Relative path for baseUrl
        open("/automation-practice-form");

        // Set basic info
        $("#firstName").setValue("Dmitry");
        $("#lastName").setValue("Endo");
        $("#userEmail").setValue("dmitry@bk.com");
        $("label[for='gender-radio-1']").shouldBe(visible, enabled).click();
        $("#userNumber").setValue("9998554545");

        // Choose 'Date of Birth' field by calendar widget
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("0");
        $(".react-datepicker__year-select").selectOptionByValue("1980");
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text("1"))
                .shouldBe(visible)
                .click();

        // Fill out 'Subjects' field
        $("#subjectsInput").setValue("math").pressEnter();
        $("#subjectsInput").setValue("art").pressEnter();
        $("#subjectsInput").setValue("eng").pressEnter();

        // Choose checkboxes for 'Hobbies'
        $("label[for='hobbies-checkbox-1']").shouldBe(visible, enabled).click();
        $("label[for='hobbies-checkbox-3']").shouldBe(visible, enabled).click();

        // File upload for 'Picture'
        File imageToUpload = new File("src/test/resources/avatar.jpg");
        $("#uploadPicture").uploadFile(imageToUpload);

        // Fill out 'Current Address' field
        $("#currentAddress").setValue("Arbat street");

        // Choose state
        $("#state").scrollIntoView(true).click();
        $$("div[id^='react-select'][class*='option']").findBy(text("NCR")).click();

        // Choose city
        $("#city").scrollIntoView(true).click();
        $$("div[id^='react-select'][class*='option']").findBy(text("Noida")).click();

        // Click 'Submit' button
        $("#submit").click();
    }
}
