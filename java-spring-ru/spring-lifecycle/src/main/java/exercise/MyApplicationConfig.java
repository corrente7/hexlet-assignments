package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Аннотация @Configuration указывает, что класс содержит методы, создающие бины
@Configuration
public class MyApplicationConfig {

        @Bean
    public Daytime getTime() {
            LocalDateTime currentDateTime = LocalDateTime.now();
        int currentHour = currentDateTime.getHour();
        if (currentHour >= 6 && currentHour < 12) {
            return new Morning();
        }
        if (currentHour >= 12 && currentHour < 18) {
            return new Day();
        }
        if (currentHour >= 18 && currentHour < 23) {
            return new Evening();
        } else
        //if ((currentHour == 23) || (currentHour >= 0 && currentHour < 6)) {
        {
            return new Night();
        }
    }


}
// END
