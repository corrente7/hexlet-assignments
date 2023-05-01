package exercise;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App{
    public static void main(String[] args) {
        String content = "environment=\"X_FORWARDED_var1=111\"\n" +
                "\n" +
                "[bar:baz]\n" +
                "environment=\"key2=true,X_FORWARDED_var2=123\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[baz:foo]\n" +
                "key3=\"value3\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make prepare'\n" +
                "\n" +
                "[program:prepare]\n" +
                "environment=\"key5=value5,X_FORWARDED_var3=value,key6=value6\"\n" +
                "\n" +
                "[program:start]\n" +
                "environment=\"pwd=/temp,user=tirion\"\n" +
                "\n" +
                "[program:options]\n" +
                "environment=\"X_FORWARDED_mail=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"\n" +
                "command=sudo -HEu tirion /bin/bash -c 'cd /usr/src/app && make environment'\n" +
                "\n" +
                "[empty]\n" +
                "command=\"X_FORWARDED_HOME=/ cd ~\"";

// Передаем содержимое файла в метод
        String result = App.getForwardedVariables(content);
        //System.out.println(result); // => "MAIL=tirion@google.com,HOME=/home/tirion,var3=value"
    }
    public static String getForwardedVariables(String s) {

        String s1 = Arrays.stream(s.split("\n"))
                .filter(str -> str.contains("environment"))
                .filter(str -> str.contains("X_FORWARDED_"))
                .map(str -> str.replaceAll("environment=", ""))
                .collect(Collectors.joining());
        String str2 = Arrays.stream(s1.split("\""))
                .filter(str -> str.contains("X_FORWARDED_"))
                .collect(Collectors.joining(","));
        String str3 = Arrays.stream(str2.split(","))
                .filter(str -> str.contains("X_FORWARDED_"))
                .map(str -> str.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        return str3;
    }
}
//END
