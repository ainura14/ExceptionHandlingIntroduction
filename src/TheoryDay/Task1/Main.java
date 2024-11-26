package TheoryDay.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int sqrt = (int)Math.sqrt(num);
        if(num != sqrt * sqrt){
            throw new RuntimeException("It is not a number which we have a sqrt.");
        }else{
            System.out.println("This is " + num + " have a sqrt.");
        }
    }
}
