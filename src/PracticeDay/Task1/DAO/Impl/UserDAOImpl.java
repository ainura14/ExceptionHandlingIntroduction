package PracticeDay.Task1.DAO.Impl;

import PracticeDay.Task1.DAO.UserDAO;
import PracticeDay.Task1.Enums.Gender;
import PracticeDay.Task1.Model.User;
import PracticeDay.Task1.db.DataBase;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;

public class UserDAOImpl implements UserDAO {
    DataBase dataBase = new DataBase();
    Scanner scannerStr = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);

    @Override
    public void register(User newUser) {
        try {
            System.out.println("Write id: ");
            newUser.setId(scannerInt.nextLong());

            System.out.println("Write user name: ");
            String name = scannerStr.nextLine();
            boolean isBlock = false;
            for (User user : dataBase.users) {
                if(user.getUserName().equalsIgnoreCase(name)){
                    isBlock = true;
                    throw new RuntimeException("User already exist.");
                }
            }
            if(!isBlock){
                newUser.setUserName(name);
            }

            // Phone number
            System.out.println("Write phone Number -> (+996 - 13 length): ");
            String phoneNumber = scannerStr.nextLine();
            if(!phoneNumber.startsWith("+996" ) && phoneNumber.length() != 13){
                throw new RuntimeException("We can't start with this format.");
            }
            newUser.setPhoneNumber(phoneNumber);

            // Gmail
            System.out.println("Write your gmail -> (gmail.com): ");
            String gmail = scannerStr.nextLine();
            if(!gmail.contains("@gmail.com")){
                throw new RuntimeException("Write true the gmail format.");
            }
            newUser.setGmail(gmail);

            // Password
            System.out.println("Write your password: ");
            String password = scannerStr.nextLine();
            if(password.length() < 4){
                throw new RuntimeException("Don't enough length to password.");
            }
            newUser.setPassword(password);

            //Gender
            System.out.println("Write your gender -> (1. Male/ 2.Female) ");
            String gender = scannerStr.nextLine();
            if(gender.equals(Gender.MALE) || gender.equals("1")){
                newUser.setGender(Gender.MALE);
            }else if(gender.equals((Gender.FEMALE)) || gender.equals("2")){
                newUser.setGender(Gender.FEMALE);
            }else{
                throw new RuntimeException("Press 1 or 2.");
            }

            dataBase.users = Arrays.copyOf(dataBase.users, dataBase.users.length + 1);
            dataBase.users[dataBase.users.length - 1] = newUser;

            System.out.println("Successfully saved user.");
            System.out.println(Arrays.toString(dataBase.users));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void login() {
        try {
            System.out.println("Write gmail: ");
            String gmail = scannerStr.nextLine();
            System.out.println("Write your password: ");
            String password = scannerStr.nextLine();
            boolean isBloc = false;
            for (User user : dataBase.users) {
                if (user.getGmail().equals(gmail) && user.getPassword().equals(password)) {
                    isBloc = true;
                    System.out.println("Hello user " + user.getUserName() + "!");
                    break;
                }
            }
            if (!isBloc) {
                throw new RuntimeException("Not found this user");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
