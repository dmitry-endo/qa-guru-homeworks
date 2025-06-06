package lesson_5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class Lesson5Tests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void GithubEnterprisePageTest() {
        // 1. На главной странице GitHub выберите:
        // Меню -> Solutions -> Enterprize (с помощью команды hover для Solutions).
        // Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").

        open("https://github.com");
        $(byTagAndText("button", "Solutions")).hover();
        $(byTagAndText("a", "Enterprises")).hover().click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
    }

    @Test
    void DragAndDropTest() {
        // 2. Запрограммируйте Drag&Drop с помощью Selenide.actions()
        // - Откройте https://the-internet.herokuapp.com/drag_and_drop
        // - Перенесите прямоугольник А на место В
        // - Проверьте, что прямоугольники действительно поменялись
        // - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()

        open("https://the-internet.herokuapp.com/drag_and_drop");
        // Moving element A to element's B place
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        // Checking that elements have changed places
        $("#column-a").shouldHave(exactText("B"));
        $("#column-b").shouldHave(exactText("A"));

        // Example with dragAndDrop() method
        $("#column-a").dragAndDrop(to("#column-b"));
        // Checking that elements have changed places
        $("#column-a").shouldHave(exactText("A"));
        $("#column-b").shouldHave(exactText("B"));
    }
}
