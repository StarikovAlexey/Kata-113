package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Max", "Star", (byte) 7);
    private static final User user2 = new User("Anna", "Star", (byte) 8);
    private static final User user3 = new User("Alex", "Star", (byte) 37);
    private static final User user4 = new User("Jack", "Poo", (byte) 45);

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());


        System.out.println(Arrays.toString(userService.getAllUsers().toArray()));

        System.out.println("Пользователи выведены");

        userService.removeUserById(2);

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
