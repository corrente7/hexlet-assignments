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
        Set<Object> setValues1 = new HashSet<>();
        Set<Object> setValues2 = new HashSet<>();
        Set<String> setKeys1 = new HashSet<>(map1.keySet());
        Set<String> setKeys2 = new HashSet<>(map2.keySet());
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> item : map1.entrySet()) {
            setValues1.add(item.getValue());
        }
        for (Map.Entry<String, Object> item : map2.entrySet()) {
            setValues2.add(item.getValue());
        }
        Set<Object> intersectionValues = new HashSet<>();
        intersectionValues.addAll(setValues1);
        intersectionValues.retainAll(setValues2);

        Set<String> intersectionKeys = new HashSet<>();
        intersectionKeys.addAll(setKeys1);
        intersectionKeys.retainAll(setKeys2);

        for (Map.Entry<String, Object> item : map1.entrySet()) {
            if (intersectionValues.contains(item.getValue())) {
                result.put(item.getKey(), "unchanged");
            } else if (intersectionKeys.contains(item.getKey()) && !intersectionValues.contains(item.getValue())) {
                result.put(item.getKey(), "changed");
            } else if (!setKeys2.contains(item.getKey())) {
                result.put(item.getKey(), "deleted");
            }
        }
        for (Map.Entry<String, Object> item : map2.entrySet()) {
            if (intersectionValues.contains(item.getValue())) {
                result.put(item.getKey(), "unchanged");
            } else if (intersectionKeys.contains(item.getKey()) && !intersectionValues.contains(item.getValue())) {
                result.put(item.getKey(), "changed");
            } else if (!setKeys1.contains(item.getKey())) {
                result.put(item.getKey(), "added");
            }
        }
        return result;
    }
}
//END
