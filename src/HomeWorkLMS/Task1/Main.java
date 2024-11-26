package HomeWorkLMS.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Write length of the cylinder: ");
            double length = scanner.nextDouble();
            validateInput(length, "Length");

            System.out.println("Write width of the cylinder: ");
            double width = scanner.nextDouble();
            validateInput(width, "Width");

            System.out.println("Write height of the cylinder: ");
            double height = scanner.nextDouble();
            validateInput(height, "Height");

            System.out.println("Write radius of the cylinder: ");
            double radius = scanner.nextDouble();
            validateInput(radius, "Radius");

            Cylinder cylinder = new Cylinder(length, width, height, radius);
            System.out.println("Information about Cylinder: ");
            System.out.println("Area: " + Math.round(cylinder.findArea()) + "\n" + "Volume: " + Math.round(cylinder.findVolume()) + "\n");
            System.out.println("-----------------------------------------------------------------------------------------------------");

            System.out.println("Write length of the parallelepiped: ");
            double length1 = scanner.nextDouble();
            validateInput(length1, "Length1");

            System.out.println("Write width of the parallelepiped: ");
            double width1 = scanner.nextDouble();
            validateInput(width1, "Width1");

            System.out.println("Write height of the parallelepiped: ");
            double height1 = scanner.nextDouble();
            validateInput(height1, "Height1");

            System.out.println("Write radius of the parallelepiped: ");
            double radius1 = scanner.nextDouble();
            validateInput(radius1, "Radius1");

            Parallelepiped parallelepiped = new Parallelepiped(length1, width1, height1, radius1);

            System.out.println("Information about Parallelepiped: ");
            System.out.println("Area: " + Math.round(parallelepiped.findArea()) + "\n" + "Volume: " + Math.round(parallelepiped.findVolume()));
        }catch (NumberFormatException e) {
            System.out.println("Error: The input is not a valid number.");
        }catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
        System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    private static void validateInput(double value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative.");
        }
    }
}
