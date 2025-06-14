package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class JavaScriptActions {

    public void removeFixedBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
