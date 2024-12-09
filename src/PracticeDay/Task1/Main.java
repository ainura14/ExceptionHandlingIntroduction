package PracticeDay.Task1;

import PracticeDay.Task1.DAO.Impl.UserDAOImpl;
import PracticeDay.Task1.DAO.UserDAO;
import PracticeDay.Task1.Model.User;
import PracticeDay.Task1.Service.Impl.UserServiceImpl;
import PracticeDay.Task1.Service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();
        UserServiceImpl userService = new UserServiceImpl(userDAO);
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Register
                    2. Login
                    """);
            String word = scannerStr.nextLine();
            switch (word) {
                case "1":
                    User user = new User();
                    userService.register(user);
                    break;
                case "2":
                    userService.login();
                    break;
                default:
                    System.out.println("Can't find this option." + word);
            }
        }
    }
}
