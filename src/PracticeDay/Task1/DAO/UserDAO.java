package PracticeDay.Task1.DAO;

import PracticeDay.Task1.Model.User;

public interface UserDAO {
    void register(User newUser);
    void login();
}
