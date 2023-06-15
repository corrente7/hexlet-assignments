package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize(Car car) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(car);
        return jsonString;

    }

    public Car unserialize(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(jsonString, Car.class);
        return car;
    }
    // END
}
