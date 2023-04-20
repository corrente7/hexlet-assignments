package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) {
        String sentence = "this class makes no guarantees as to the order of the map";
        Map wordsCount = getWordCount(sentence);
        String result = toString(wordsCount);
        System.out.println(result);
        Map wordsCount2 = getWordCount("");
        String result2 = toString(wordsCount2);
        System.out.println(result2);
    }

    public static Map getWordCount(String sentence) {
        Map<String, Integer> map = new HashMap<>();
        String[] arrayWords = sentence.split(" ");
        if (arrayWords[0].equals("")) {
            map.clear();
            return map;
        }
        for (String str : arrayWords) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else
            {
                map.put(str, map.get(str) + 1);
            }
        }
        return map;
    }
    public static String toString(Map<String, Integer> hmap) {
        String all = "";
        if (hmap.isEmpty()) {
            return "{}";
        }
        for (Map.Entry<String, Integer> entry: hmap.entrySet()) {
            String str = "  " + entry.getKey() + ": "  +  entry.getValue() + '\n';
            all = all.concat(str);
        }
        return "{\n" + all + "}";
    }
}
//END
