package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root1";
    private static final String URL = "jdbc:MySQL://localhost/pptest?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static SessionFactory getSessionFactory() {
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();
//        return sessionFactory;
//    }

//    public static SessionFactory getSessionFactory() {
//        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//        Map<String, String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, DRIVER);
//        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        settings.put(Environment.URL, URL);
//        settings.put(Environment.USER, USER_NAME);
//        settings.put(Environment.PASS, PASSWORD);
//        registryBuilder.applySettings(settings);
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(settings).build();
//        MetadataSources metadataSources = new MetadataSources(serviceRegistry)
//                .addAnnotatedClass(User.class);
//        SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
//        return sessionFactory;
//    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", USER_NAME);
        properties.setProperty("hibernate.connection.password", PASSWORD);
        properties.setProperty("hibernate.connection.driver_class", DRIVER);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}

