package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();
    void updateUser(long id, User user);
    void deleteUser(long id);
    User findUserId(long id);
}
