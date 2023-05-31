package exercise;

// BEGIN
public class Cottage implements Home{
private double area;
private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        if (this.getArea() > another.getArea()) {
            return 1;
        }
        if (this.getArea() < another.getArea()) {
            return -1;
        }
        if (this.getArea() == another.getArea()) {
            return 0;
        }
        return 11;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}
// END
