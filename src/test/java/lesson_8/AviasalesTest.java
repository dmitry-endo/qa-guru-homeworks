package lesson_8;

import com.codeborne.selenide.Configuration;
import lesson_8.data.AviasalesNavTab;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты для сайта Aviasales")
public class AviasalesTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "https://www.aviasales.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {
            "Россия",
            "Казахстан",
            "Узбекистан"
    })
    @ParameterizedTest(name = "Раздел Короче должен показывать результаты для страны {0}")
    void guidesPageSearchShouldHaveResultsByCountry(String searchQuery) {
        open("/guides");
        $("#travel_map_cities_search").setValue(searchQuery);
        $$("section div").shouldHave(itemWithText(searchQuery));
    }

    @CsvSource(value = {
            "/, Тут покупают дешёвые авиабилеты",
            "/hotels, Здесь бронируют балдёжные отели",
            "/guides, Короче о городах"
    })
    @ParameterizedTest(name = "Раздел сайта {0} должен содержать заголовок {1}")
    @DisplayName("Разделы сайта должны содержать заголовки")
    void allTabsShouldHaveExpectedTitleCsvSource(String url, String expectedText) {
        open(url);
        $("body").shouldHave(text(expectedText));
    }

    @CsvFileSource(resources = "/test_data/allTabsShouldHaveExpectedTitleCsvFileSource.csv")
    @ParameterizedTest(name = "Раздел сайта {0} должен содержать заголовок {1}")
    @DisplayName("Разделы сайта должны содержать заголовки")
    void allTabsShouldHaveExpectedTitleCsvFileSource(String url, String expectedText) {
        open(url);
        $("body").shouldHave(text(expectedText));
    }

    static Stream<Arguments> airlinesPagesShouldHaveExpectedTitle() {
        return Stream.of(
                Arguments.of("/airlines/rossiya-airlines", "Дешёвые авиабилеты авиакомпании Россия"),
                Arguments.of("/airlines/aeroflot", "Дешёвые авиабилеты авиакомпании Аэрофлот"),
                Arguments.of("/airlines/victory", "Дешёвые авиабилеты авиакомпании Победа")
        );
    }

    @MethodSource()
    @ParameterizedTest(name = "Для раздела {0} должен отображаться заголовок {1}")
    @DisplayName("Для разделов по конкретным авиакомпаниям должен отображаться корректный заголовок")
    void airlinesPagesShouldHaveExpectedTitle(String pageUrl, String expectedTitle) {
        open(pageUrl);
        $("[data-test-id='page-header']").shouldHave(text(expectedTitle));
    }

    @EnumSource(AviasalesNavTab.class)
    @ParameterizedTest(name = "Вкладка {0} должна присутствовать на главной странице")
    @DisplayName("Должны присутствовать все основные вкладки на главной станице")
    void aviasalesTabsShouldBeVisible(AviasalesNavTab tab) {
        open("/");
        $$("nav ul").findBy(text(tab.title)).shouldBe(visible);
    }
}
