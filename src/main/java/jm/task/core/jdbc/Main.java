package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Joe", "Peach", (byte) 28);
        service.saveUser("John", "Wane", (byte) 48);
        service.saveUser("Dirty", "Monke", (byte) 18);
        service.saveUser("Donald", "Trump", (byte) 78);
        var users = service.getAllUsers();
        for (var user : users) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
        //service.removeUserById(1);
    }
}
