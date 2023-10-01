package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCityWeather(
            @PathVariable long id) throws JsonProcessingException {

        City city = cityRepository.findById(id);
        if (city == null) {
            new CityNotFoundException("User not found");
        }
        return weatherService.getWeather(city);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> searchWeather(
            @RequestParam(defaultValue = "") String name) throws JsonProcessingException {

        List<City> cities;

        if (name.equals("")) {
            cities = cityRepository.findAllByOrderByName();
        }
        else {
           cities = cityRepository.findByNameStartingWithIgnoreCase(name);
}

        List<Map<String, String>> list = new ArrayList<>();

        for (City city : cities) {
            list.add(weatherService.getWeather(city));
        }
        System.out.println(list);

        return list.stream()
                .map(x -> {
                    x.remove("cloudy");
                    x.remove("wind");
                    x.remove("humidity");
                    return x;
                })
                .collect(Collectors.toList());


    }
    // END
}

