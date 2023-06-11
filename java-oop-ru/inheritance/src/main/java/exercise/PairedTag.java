package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    String tagBody;
    List<Tag> children;

    public PairedTag(String tag, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tag, attributes);
        this.tagBody = tagBody;
        this.children = children;
    }

    @Override
    public String toString() {
        String temp = super.toString();
        if (children == null) {
            return temp + tagBody + "</" + tag + ">";
        } else {
            String allChildren = "";
            for (Tag s: children) {
                allChildren = allChildren.concat(s.toString());
            }
            return temp + tagBody + allChildren + "</" + tag + ">";
            }
        }

    }
// END
