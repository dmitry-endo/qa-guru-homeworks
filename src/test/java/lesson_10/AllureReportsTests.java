package lesson_10;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты на проверку наименования вкладки Issues в репозитории на GitHub")
public class AllureReportsTests {
    private static final String REPOSITORY = "dmitry-endo/qa-guru-homeworks";
    private static final String ISSUES = "Issues";

    @BeforeEach
    public void listenerSetUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение вкладки Issues")
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка наименования вкладки Issues с помощью чистого Selenide")
    public void checkIssuesTabNameTest() {
        open("https://github.com");

        actions().sendKeys("/").perform();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();

        $(linkText(REPOSITORY)).click();

        $("#issues-tab").shouldHave(text(ISSUES));
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение вкладки Issues")
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка наименования вкладки Issues с помощью lambda step method")
    public void checkIssuesTabNameWithStepMethodsTest() {
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            actions().sendKeys("/").perform();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверяем название вкладки Issues в репозитории", () -> {
            $("#issues-tab").shouldHave(text(ISSUES));
        });
    }

    @Test
    @Feature("Issues в репозитории")
    @Story("Отображение вкладки Issues")
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверка наименования вкладки Issues с помощью @Step annotation")
    public void checkIssuesTabNameWithAnnotatedStepsTest() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.checkIssuesTabName(ISSUES);
    }
}
