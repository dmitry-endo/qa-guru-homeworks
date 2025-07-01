package lesson_9;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка парсинга файлов из zip архива")
public class FilesParsingTest {

    private final ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private final String pathToZipFile = "test_data/example.zip";

    @Test
    @DisplayName("Проверка автора и кол-ва страниц в PDF файле (вложен в zip архив)")
    void pdfFileShouldContainExpectedAuthorAndNumberOfPagesTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(pathToZipFile))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);

                    assertThat(pdf.author).contains("Michael Sorens");
                    assertThat(pdf.numberOfPages).isEqualTo(1);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка содержимого ячейки и кол-ва листов в Excel файле (вложен в zip архив)")
    void excelFileShouldContainExpectedCellValueAndNumberOfSheetsTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(pathToZipFile))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    XLS xls = new XLS(zis);

                    assertThat(xls.excel.getSheetAt(0)
                            .getRow(5).getCell(0).getStringCellValue())
                            .isEqualTo("Ubuntu 22.04");
                    assertThat(xls.excel.getNumberOfSheets()).isEqualTo(1);
                }
            }
        }
    }

    @CsvSource(value = {
            "/, Тут покупают дешёвые авиабилеты",
            "/hotels, Здесь бронируют балдёжные отели",
            "/guides, Короче о городах"
    })
    @ParameterizedTest(name = "CSV файл должен пару значений: {0},{1}")
    @DisplayName("Проверка ожидаемых значений в CSV файле (вложен в zip архив)")
    void csvFileShouldContainExpectedRowValuesTest(String rowValue1, String rowValue2) throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(pathToZipFile))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.endsWith(".csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvData = csv.readAll();

                    assertThat(csvData).contains(new String[] {rowValue1, rowValue2});
                }
            }
        }
    }
}
