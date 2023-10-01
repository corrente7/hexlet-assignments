package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {

        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(City city) throws JsonProcessingException {
        Map<String, String> result = null;
        ObjectMapper mapper = new ObjectMapper();

        String response = client.get("http://weather/api/v2/cities/" + city.getName());
        result = mapper.readValue(response, Map.class);

        System.out.println("City not found");

        return result;

    }

    // END
}
