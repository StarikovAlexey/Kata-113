package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static Logger log = Logger.getLogger(UserDaoJDBCImpl.class);

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Alan", "Star", (byte) 37);
        userService.saveUser("Anna", "Star", (byte) 35);
        userService.saveUser("Max", "Light", (byte) 8);
        userService.saveUser("Jack", "Poo", (byte) 45);
        log.info("Users in table: ");
        log.info(Arrays.toString(userService.getAllUsers().toArray()));
        userService.removeUserById(2);
        log.info("Users in table: ");
        log.info(Arrays.toString(userService.getAllUsers().toArray()));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
