package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl kek = new UserServiceImpl();
        // создание таблицы
        kek.createUsersTable();
        // добавление 4-х пользователей
        kek.saveUser("Джон", "Дориан", (byte) 23);
        kek.saveUser("Персиваль", "Кокс", (byte) 46);
        kek.saveUser("Роберт", "Келсо", (byte) 65);
        kek.saveUser("Убо", "Рщик", (byte) 45);
        // получение списка пользователей и вывод в консоль
        System.out.println(kek.getAllUsers());
        // очищение списка пользователей
        kek.cleanUsersTable();
        // удаление таблицы пользователей
        kek.dropUsersTable();
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