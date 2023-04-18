package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println(scrabble("rkqodlw", "world"));
        System.out.println(scrabble("ajv", "java")); // false
        System.out.println(scrabble("avjafff", "JaVa")); // true
        System.out.println(scrabble("", "hexlet")); // false
    }

    public static boolean scrabble(String charset, String word) {
        int count = 0;
        boolean mark = false;
        String charsetIgnoreCase = charset.toLowerCase();
        String wordIgnoreCase = word.toLowerCase();
        List<Character> chars = new ArrayList<>();
        for (char ch : charsetIgnoreCase.toCharArray()) {
            chars.add(ch);
        }
        //System.out.println(chars);
        char[] arrayWord = wordIgnoreCase.toCharArray();
        for (int i = 0; i < arrayWord.length; i++) {
            if (chars.contains(arrayWord[i])) {
                int pos = chars.indexOf(arrayWord[i]);
                chars.remove(pos);
                mark = true;
            } else {
                mark = false;
                break;
            }
        }

        return mark;
    }
}

//END
