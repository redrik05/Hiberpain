package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getStatement;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class).executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE USERS").executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName,age));
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            transaction.rollback();
            transaction = null;
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            transaction.rollback();
            transaction = null;
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> us = new ArrayList<>();
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            us =  session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            transaction.rollback();
            transaction = null;
            e.printStackTrace();
        }
        return us;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            transaction.rollback();
            transaction = null;
            e.printStackTrace();
        }
    }
}
