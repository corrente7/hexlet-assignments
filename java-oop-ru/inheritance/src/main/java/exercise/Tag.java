package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    String tag;
    Map<String, String> attributes;

    public Tag(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    //<тег атрибут1="значение1" атрибут2="значение2">
    @Override
    public String toString() {
        String attr = "";
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attr = attr.concat(" " + entry.getKey() + "=\"" + entry.getValue() + "\"");
        }
        return "<" + tag + attr + ">";
    }
}
// END
