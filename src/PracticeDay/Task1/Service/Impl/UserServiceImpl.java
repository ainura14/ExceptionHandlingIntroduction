package PracticeDay.Task1.Service.Impl;

import PracticeDay.Task1.DAO.Impl.UserDAOImpl;
import PracticeDay.Task1.Model.User;
import PracticeDay.Task1.Service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAO;

    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) {
        userDAO.register(user);
    }

    @Override
    public void login() {
        userDAO.login();
    }
}
