package HomeWorkLMS.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write length of the cylinder: ");
        try {
            double length = scanner.nextDouble();
            if (length < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }


            System.out.println("Write width of the cylinder: ");
            double width = scanner.nextDouble();
            if (width < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }
            System.out.println("Write height of the cylinder: ");
            double height = scanner.nextDouble();
            if (height < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }
            System.out.println("Write radius of the cylinder: ");
            double radius = scanner.nextDouble();
            if (radius < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }

            Cylinder cylinder = new Cylinder(length, width, height, radius);
            System.out.println("Information about Cylinder: ");
            System.out.println("Area: " + Math.round(cylinder.findArea()) + "\n" + "Volume: " + Math.round(cylinder.findVolume()) + "\n");
            System.out.println("-----------------------------------------------------------------------------------------------------");

            System.out.println("Write length of the parallelepiped: ");
            double length1 = scanner.nextDouble();
            if (length1 < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }

            System.out.println("Write width of the parallelepiped: ");
            double width1 = scanner.nextDouble();
            if (width1 < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }
            System.out.println("Write height of the parallelepiped: ");
            double height1 = scanner.nextDouble();
            if (height1 < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }
            System.out.println("Write radius of the parallelepiped: ");
            double radius1 = scanner.nextDouble();
            if (radius1 < 0) {
                throw new IllegalArgumentException("Length cannot be negative.");
            }

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
}
