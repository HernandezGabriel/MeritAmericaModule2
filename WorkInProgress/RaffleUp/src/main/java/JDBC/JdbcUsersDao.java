package JDBC;

import DBModels.Users;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUsersDao implements UserDao{
    public JdbcUsersDao(DataSource dataSource) {

    }

    @Override
    public Users createUserReturnNewUser(Users user) {
        return null;
    }

    @Override
    public void deleteUserUsingId(long id) {

    }

    @Override
    public void autheniticateUser(String username, String password) {

    }

    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return false;
    }
}
