package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path filepath, Car car) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String CarString = objectMapper.writeValueAsString(car);
        Files.writeString(filepath, CarString);
    }

    public static Car extract(Path filepath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(Files.readString(filepath), Car.class);
        return car;
    }
}
// END
