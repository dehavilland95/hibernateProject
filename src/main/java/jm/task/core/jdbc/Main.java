package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl service = new UserServiceImpl();
        //service.createUsersTable();
        //service.dropUsersTable();
        //service.saveUser("Donald", "Trumnp", (byte) 77);
        //service.removeUserById(1);
        service.cleanUsersTable();
//        var users = service.getAllUsers();
//        for (var user : users) {
//            System.out.println(user);
//        }
    }
}
