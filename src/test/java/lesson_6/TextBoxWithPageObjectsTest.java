package lesson_6;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import tests.TestBase;

public class TextBoxWithPageObjectsTest extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillTextBoxPage() {

        String fullName = "Dmitry Endo";
        String userEmail = "dmitry@bk.com";
        String currentAddress = "Arbat street";
        String permanentAddress = "Leningradsky prospekt, 36";

        textBoxPage.openPage()
                .setFullName(fullName)
                .setUserEmail(userEmail)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();

        textBoxPage.checkResult("Name:" + fullName)
                .checkResult("Email:" + userEmail)
                .checkResult("Current Address :" + currentAddress)
                .checkResult("Permananet Address :" + permanentAddress);
    }
}
