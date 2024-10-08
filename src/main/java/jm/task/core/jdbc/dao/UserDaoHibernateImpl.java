package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getHibernateConnection();
    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users
                (
                    id       SERIAL PRIMARY KEY,
                    name     VARCHAR(255),
                    lastName VARCHAR(255),
                    age      INT
                );
                """;
        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = """
               DROP TABLE IF EXISTS users;
                """;
        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if(user == null){
                session.getTransaction().commit();
                throw new RuntimeException("User not found");
            }
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = """
               SELECT name, lastName, age FROM users;
                """;
        List<User> users;

        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = """
               TRUNCATE TABLE users;
                """;
        try(var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
