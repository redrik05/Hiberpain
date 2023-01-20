package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getStatement;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            st.executeUpdate("CREATE TABLE MyUsersTest("
                    + "id_user mediumint(9) auto_increment, "
                    + "name_user char(50), "
                    + "last_name char(50), "
                    + "age mediumint(3), "
                    + "PRIMARY KEY (id_user) "
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            String sql = "DROP DATABASE USERS";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            String sql = "insert into myuserstest (name_user, last_name, age) values({name}, {lastName}, {age});";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
