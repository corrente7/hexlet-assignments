package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String path;
    private Map<String, String> initial = new HashMap<>();

    public FileKV(String path, Map<String, String> initial) {
        this.path = path;
        this.initial.putAll(initial);
    }

    @Override
    public void set(String key, String value) {
        String readPath = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(readPath);
        map.put(key, value);
        Utils.writeFile(this.path, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        String readPath = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(readPath);
        map.remove(key);
        Utils.writeFile(this.path, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        String readPath = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(readPath);
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String readPath = Utils.readFile(path);
        Map<String, String> map = Utils.unserialize(readPath);
        return new HashMap<>(map);
    }
}
// END
