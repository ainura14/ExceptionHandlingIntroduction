package HomeWorkLMS.Task1;

public class Figures {
    private double length;
    private double width;
    private double height;
    private double radius;

    public Figures() {
    }

    public Figures(double length, double width, double height, double radius) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Figures{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", radius=" + radius +
                '}';
    }
}
