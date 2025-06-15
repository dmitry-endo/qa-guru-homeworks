package pages.components;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HashTableComponent {
    public void positiveCheck(String key, String value) {
        $(".table-responsive")
                .$(byText(key)).parent().shouldHave(text(value));
    }

    public void negativeCheck(String key) {
        $(".table-responsive")
                .$(byText(key)).parent().shouldBe(empty);
    }
}
