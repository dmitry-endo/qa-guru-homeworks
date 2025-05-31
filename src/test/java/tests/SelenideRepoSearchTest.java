package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepoSearchTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void SelenideRepoTest() {
        // Задание:
        // Откройте страницу Selenide в Github
        // Перейдите в раздел Wiki проекта
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        String firstExampleCode = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;
        String secondExampleCode = """
                class Tests {
                  @RegisterExtension\s
                  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();
                
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;

        open("/");
        actions().sendKeys("/").perform(); // Opening input field by hotkey
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("[data-testid='results-list']").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));

        // Opening wiki tap
        $("#wiki-tab").click();
        // Checking if SoftAssertions page exist inside Pages list
        $$("[data-filterable-for='wiki-pages-filter']")
                .last()
                .scrollIntoView(true)
                .$("button")
                .click();
        $$("#wiki-pages-box").shouldHave(texts("SoftAssertions"));

        // Opening SoftAssertions page
        $(byTagAndText("a", "SoftAssertions")).scrollIntoView(true).click();
        // Checking if there's JUnit5 example code
        $(byTagAndText("h4", "3. Using JUnit5 extend test class:"))
                .parent().sibling(0).$("pre").shouldHave(text(firstExampleCode));
        // Checking if there's another JUnit5 example code
        $(byTagAndText("p", "Or register extension inside test class:"))
                .sibling(0).$("pre").shouldHave(text(secondExampleCode));
    }
}
