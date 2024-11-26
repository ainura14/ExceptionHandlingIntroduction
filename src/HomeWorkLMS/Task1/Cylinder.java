package HomeWorkLMS.Task1;

import static java.lang.Math.PI;

public class Cylinder extends Figures implements Methods{
    public Cylinder() {
    }

    public Cylinder(double length, double width, double height, double radius) {
        super(length, width, height, radius);
    }

    @Override
    public double findArea() {
        return 2 * PI * getRadius() * ( getHeight() + getRadius());
    }

    @Override
    public double findVolume() {
        return PI * getRadius() * getRadius() * getHeight();
    }
}
