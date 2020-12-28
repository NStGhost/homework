package com.shadow.homework22.OnLesson;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Logger;

public class MySQL_Connector {
    private Object lock = new Object();

    private final Logger logger;
    private Connection connection;

    private final String urlConnector = "jdbc:sqlite:testdb.db";
    private final long timeoutReconnect = 1_000;
    private final long timeoutReconnectAfterMaxReconnect = 30_000;
    private final int maxCountReconnects = 50;
    private int currentCountReconnect = 0;

    public MySQL_Connector(Logger logger) {
        this.logger = logger;
        connect();
    }

    private void connect() {
        synchronized (lock) {
            try {
                this.connection = DriverManager.getConnection(urlConnector);
            } catch (SQLException throwables) {
                reConnect();
            }
        }
    }

    public Connection getConnect() {
        synchronized (lock) {
            return this.connection;
        }
    }

    public void reConnect() {
        synchronized (lock) {
            if (currentCountReconnect > maxCountReconnects) { sleepThread(timeoutReconnectAfterMaxReconnect); }
            sleepThread(timeoutReconnect);


            try {
                this.connection = DriverManager.getConnection(urlConnector);
                currentCountReconnect = 0;
            } catch (SQLException throwables) {
                currentCountReconnect++;
                reConnect();
            }
        }
    }

    //SELECT, CREATE TABLE, INSERT, UPDATE
    public boolean executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            //log.write(e.getMessage());
            return false;
        }
    }

    //update, select
    public Optional<Object> execute(String query) {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) { return Optional.empty();}
            return null;
        } catch (SQLException e) {
            //log.write(e.getMessage());
            return Optional.empty();
        }
    }


    private void sleepThread(long timeSleep) {
        try {
            Thread.sleep(timeoutReconnect);
        } catch (InterruptedException e) {

        }
    }

    public void closeConnect() {
        try { connection.close(); } catch (SQLException e) { }
    }

}
