package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Alan", "Star", (byte) 36);
    private static final User user2 = new User("Jack", "Daniels", (byte) 7);
    private static final User user3 = new User("Jesus", "Chryst", (byte) 33);
    private static final User user4 = new User("Arthur", "Pirozkov", (byte) 45);

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем – name добавлен в базу данных");

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем – name добавлен в базу данных");

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем – name добавлен в базу данных");

        userService.saveUser((user4.getName()), user4.getLastName(), user4.getAge());
        System.out.println("User с именем – name добавлен в базу данных");

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
