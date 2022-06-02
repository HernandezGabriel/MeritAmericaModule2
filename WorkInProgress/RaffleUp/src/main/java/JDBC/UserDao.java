package JDBC;

import DBModels.Users;

import java.util.List;

public interface UserDao {

    Users createUserReturnNewUser(Users user);
    void deleteUserUsingId(long id);
    void autheniticateUser(String username,String password);
    List<Users> getAllUsers();
    boolean checkIfUsernameExists(String username);

}
