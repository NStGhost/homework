package com.shadow.homework22.HomeWork22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void run() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/test123?useUnicode=true&serverTimezone=Europe/Moscow";
        final String user = "root";
        final String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            hadlerConnection(connection);
        }

    }

    private void hadlerConnection(Connection connection) {

    }

}
