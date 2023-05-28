package exercise;

public class Example {
    public static void main(String[] args) {

        Point point1 = new Point(4, 3);
        Point point2 = new Point(6, 1);
        Segment segment = new Segment(point1, point2);
        Point midPoint = segment.getMidPoint();
        System.out.println(midPoint.getX()); // 5
        System.out.println(midPoint.getY()); // 2
    }
}
