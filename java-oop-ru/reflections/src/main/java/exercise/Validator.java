package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        notValidFields.put(field.getName(), List.of("can not be null"));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength MinLengthInfo = field.getAnnotation(MinLength.class);
                    try {
                        field.setAccessible(true);
                        String str = (String) field.get(address);
                        if (str.length() < MinLengthInfo.minLength()) {
                            notValidFields.put(field.getName(), List.of("length less than " + MinLengthInfo.minLength()));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        }
        return notValidFields;
    }
}
// END
