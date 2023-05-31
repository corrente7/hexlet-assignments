package exercise;

import java.lang.String;

// BEGIN
public class ReversedSequence implements CharSequence{

    private String string;

    public ReversedSequence(String string) {
        this.string = new StringBuffer(string).reverse().toString();
    }

    @Override
    public int length() {
        char[] chars = this.string.toCharArray();
        return chars.length;
    }

    @Override
    public char charAt(int i) {
        char[] chars = this.string.toCharArray();
        return chars[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.string.substring(i, i1);
    }

    @Override
    public String toString() {
        return string;
    }
}
// END
