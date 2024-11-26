package HomeWorkLMS.Task1;

public class Parallelepiped extends Figures implements Methods{
    public Parallelepiped() {
    }

    public Parallelepiped(double length, double width, double height, double radius) {
        super(length, width, height, radius);
    }

    @Override
    public double findArea() {
        return  2 * ((getHeight() * getLength()) + (getLength() * getWidth()) + (getHeight() * getWidth()));
    }

    @Override
    public double findVolume() {
        return getLength() * getWidth() * getHeight();
    }
}
