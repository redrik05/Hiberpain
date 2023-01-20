package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getStatement;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {

            st.executeUpdate("CREATE TABLE user("
                    + "id mediumint(9) auto_increment, "
                    + "name char(50), "
                    + "lastName char(50), "
                    + "age mediumint(3), "
                    + "PRIMARY KEY (id) "
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            String sql = "DROP Table user";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = getConnection()) {
            String sql = "insert into user (name, lastName, age) values (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            String sql = "DELETE FROM user WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
            String sql = "select * from user";
            ResultSet resultSet =  st.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = getConnection(); Statement st = getStatement(con)) {
        String sql = "DELETE FROM user";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
