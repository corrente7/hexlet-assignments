package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App{
    public static void main(String[] args) {
        Home flat = new Flat(54.5, 4, 3);
        double area = flat.getArea(); // 58.5
        flat.toString();

        Home cottage = new Cottage(135, 2);
        double area1 = cottage.getArea(); // 135
        cottage.toString(); // "2 этажный коттедж площадью 135 метров"

        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);

        CharSequence text = new ReversedSequence("abcdef");
        System.out.println(text); // "fedcba"
        System.out.println(text.charAt(1)); // 'e'
        System.out.println(text.length()); // 6
        System.out.println(text.subSequence(1, 4)); // "edc"
    }


    public static List<String> buildApartmentsList(List<Home> properties, int amount){
        return properties.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .limit(amount)
                .map(s -> s.toString())
                .collect(Collectors.toList());
    }
}
// END
