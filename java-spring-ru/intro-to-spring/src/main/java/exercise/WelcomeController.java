package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
// Класс контроллера
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    // Обработчик
    // Привязываем параметр запроса к параметру метода
    // и задаём значение по умолчанию
    public String HelloUser(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
// END
