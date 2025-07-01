package lesson_9;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_9.model.Drivers;
import lesson_9.model.TaxiDrivers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка парсинга файлов из JSON файла")
public class JsonParsingTest {

    private final ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private final String pathToJsonFile = "test_data/taxi_drivers.json";

    @Test
    @DisplayName("Общее число водителей должно быть равно размеру массива с водителями")
    void totalDriversValueShouldMatchDriversArraySizeTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream(pathToJsonFile))) {
            ObjectMapper mapper = new ObjectMapper();
            TaxiDrivers taxiDriversData = mapper.readValue(reader, TaxiDrivers.class);

            assertThat(taxiDriversData.getDrivers())
                    .hasSize(taxiDriversData.getTotalDrivers());
        }
    }

    @Test
    @DisplayName("У водителя на обучении не должно быть назначенной машины")
    void trainingDriverShouldNotHaveVehicleTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream(pathToJsonFile))) {
            ObjectMapper mapper = new ObjectMapper();
            TaxiDrivers taxiDriversData = mapper.readValue(reader, TaxiDrivers.class);
            List<Drivers> drivers = taxiDriversData.getDrivers();

            Drivers trainingDriver = drivers.stream()
                    .filter(d -> "training".equalsIgnoreCase(d.getStatus()))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Training driver not found"));

            assertThat(trainingDriver.getVehicleAssigned())
                    .as("Training driver should not have an assigned vehicle")
                    .isNull();
        }
    }
}
