package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static void main(String[] args) {
        String[] emails = {
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
        };

        List<String> emailsList = Arrays.asList(emails);
        App.getCountOfFreeEmails(emailsList);
    }

    public static long getCountOfFreeEmails(List<String> emails) {
        long count = 0;
        if (emails != null) {
         count = emails.stream()
                 .filter(s -> s.contains("@gmail.com") || s.contains("@yandex.ru") || s.contains("@hotmail.com"))
                 .collect(Collectors.counting());
            System.out.println(count);
        } else {
            System.out.println("List is null");
        }
        return count;
    }
}
// END
