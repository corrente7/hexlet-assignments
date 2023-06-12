package exercise;

// BEGIN
public class Circle {
    Point point;
    public static int raduis;


    public Circle(Point point, int raduis) {
        this.point = point;
        this.raduis = raduis;
    }

    public static double getSquare() throws NegativeRadiusException {
        if (raduis < 0 ) {
            throw new NegativeRadiusException("Exception");
        }
        return Math.PI * Math.pow(raduis, 2);
    }

    public int getRadius() {
        return raduis;
    }
}
// END
