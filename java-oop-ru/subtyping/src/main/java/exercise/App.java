package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

// BEGIN
public class App{
    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "10"));
        // Получение значения по ключу
        storage.get("key", "default"); // "10"
        storage.get("unknown", "default"); // "default"
        // Установка нового значения
        storage.set("key2", "value2");
        storage.get("key2", "default"); // "value2"
        // Удаление ключа
        storage.unset("key2");
        storage.get("key2", "default"); // "default"
        // Получение всех данных из базы
        Map<String, String> data = storage.toMap();
        System.out.println(data); // => {key=10};
    }
    public static void swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> map: storage.toMap().entrySet()) {
            storage.unset(map.getKey());
            storage.set(map.getValue(), map.getKey());
        }
    }

}
// END
