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
        String sql = "CREATE TABLE IF NOT EXISTS users" +
                "(id INT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(45) NOT NULL," +
                " lastname VARCHAR(45) NOT NULL," +
                " age INT NOT NULL," +
                "PRIMARY KEY(id))";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            log.info("Таблица создана!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            log.info("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO kata_test.users(name, lastname, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            log.info("Пользователь с именем " + name + " и фамилией " + lastName + " добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            log.info("Пользователь с ID " + id + " удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT id, name, lastname, age FROM users";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            log.info("Таблица очищена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
