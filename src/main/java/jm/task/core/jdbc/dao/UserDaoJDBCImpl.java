package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoJDBCImpl.class);

    Connection connection = Util.getConnection();

    public void createUsersTable() {
        Connection connection = null;
        String sql = "CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45)," +
                "lastName VARCHAR(45)," +
                "age TINYINT NOT NULL, PRIMARY KEY (id))";
        try {
            connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            statement.execute();
            connection.commit();
            log.info("Table created!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                log.error("Error rollback");
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException exe) {
                    exe.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE User";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Table deleted!");
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO User(name, lastname, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("User " + name + " " + lastName + " added!");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM User WHERE ID = ?";
        try {
            Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("User ID " + id + " deleted!");
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String jpql = "SELECT * FROM User";

        try {
            PreparedStatement statement = connection.prepareStatement(jpql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE User";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Table cleared");
    }
}
