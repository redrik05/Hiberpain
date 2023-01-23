package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao us = new UserDaoHibernateImpl();

        // создание таблицы
        us.createUsersTable();
        // добавление 4-х пользователей
        us.saveUser("Джон", "Дориан", (byte) 23);
        us.saveUser("Персиваль", "Кокс", (byte) 46);
        us.saveUser("Роберт", "Келсо", (byte) 65);
        us.saveUser("Убо", "Рщик", (byte) 45);
        // получение списка пользователей и вывод в консоль
        System.out.println(us.getAllUsers());
        // очищение списка пользователей
        us.cleanUsersTable();
        // удаление таблицы пользователей
        us.dropUsersTable();
    }
}
        /*
        Операции, которые нужно проделать в меин
            - Создание таблицы User(ов)
            - Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
            - Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
            - Очистка таблицы User(ов)
            - Удаление таблицы
        */