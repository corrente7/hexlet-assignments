package exercise;

import java.util.*;

// BEGIN
class App {
    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true)
        );
        System.out.println(data1); //=> {two=two, four=true, one=eon}

        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(data2); //=> {zero=4, two=own, four=true}

        Map<String, String> result = App.genDiff(data1, data2);
        System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
    }

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        Set<String> unionKeys = new HashSet<>();
        unionKeys.addAll(map1.keySet());
        unionKeys.addAll(map2.keySet());

        for (String key : unionKeys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.put(key, "deleted");
            } else if (map2.containsKey(key) && !map1.containsKey(key)) {
                result.put(key, "added");
            } else if (map1.containsKey(key) && map2.containsKey(key) && !map1.get(key).equals(map2.get(key))) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }
            return result;
    }
}
//END
