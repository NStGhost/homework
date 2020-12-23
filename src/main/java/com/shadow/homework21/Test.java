package com.shadow.homework21;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends JFrame {

    private String textResult = "Результат: - %s";

    private String messageToUser = "Enter command\n" +
            "1 - create table student\n" +
            "2 - delete table\n" +
            "3 - add student\n" +
            "4 - show all students\n" +
            "5 - search student\n" +
            "-1 - Exit";

    public Test() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:testdb.db")) {
            while (true) {
                System.out.println(messageToUser);
                Scanner scanner = new Scanner(System.in);
                boolean isExit = false;
                boolean check;
                switch (scanner.next()) {
                    case "1":
                        check = execute("CREATE TABLE Student (id INTEGER, name varchar(255), firstName varchar(255), year INTEGER)", connection);
                        if (check) {
                            System.out.println("Table create");
                        } else {
                            System.out.println("Table wasn't create");
                        }
                        break;
                    case "2":
                        check = execute("DROP TABLE Student", connection);
                        if (check) {
                            System.out.println("Table DROP");
                        } else {
                            System.out.println("Table wasn't DROP");
                        }
                        break;
                    case "3":
                        String[] student = new String[4];
                        System.out.println("Enter id student");
                        student[0] = scanner.next();
                        System.out.println("Enter name student");
                        student[1] = scanner.next();
                        System.out.println("Enter firstName student");
                        student[2] = scanner.next();
                        System.out.println("Enter year student");
                        student[3] = scanner.next();

                        String query = String.format("INSERT INTO Student VALUES(%d, '%s', '%s', %d);",
                                Integer.valueOf(student[0]), student[1], student[2], Integer.valueOf(student[3]));
                        check = execute(query, connection);
                        if (check) {
                            System.out.println("Students add");
                        } else {
                            System.out.println("Students wasn't add");
                        }

                        break;
                    case "4":
                        query = "SELECT * FROM Student ORDER BY name;";
                        ArrayList<StudentTemp> temp = executeSelect(query, connection);
                        for (StudentTemp temp1 : temp) {
                            System.out.println(String.format("Id - %d, Name - %s, First Name - %s, Year - %d",
                                    temp1.id, temp1.name, temp1.firstName, temp1.year));
                        }
                        break;
                    case "5":
                        System.out.println("Enter firstname or surname student");
                        String argSearch = scanner.next();
                        query = String.format("SELECT * FROM Student WHERE name='%s' or firstName='%s' ORDER BY name, firstName", argSearch, argSearch);
                        temp = executeSelect(query, connection);
                        for (StudentTemp temp1 : temp) {
                            System.out.println(String.format("Id - %d, Name - %s, First Name - %s, Year - %d",
                                    temp1.id, temp1.name, temp1.firstName, temp1.year));
                        }
                        break;
                    case "-1":
                        isExit = true;
                        break;
                    default:
                        System.out.println("Enter true command");
                        break;
                }
                if (isExit) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean execute(String query, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private ArrayList<StudentTemp> executeSelect(String query, Connection connection) {
        ArrayList<StudentTemp> studentTemp = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                studentTemp.add(new StudentTemp(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("firstName"),
                        resultSet.getInt("year")));
            }
            return studentTemp;
        } catch (Exception e) {
            e.printStackTrace();
            return studentTemp;
        }
    }

    public static void main(String[] args) {
        new Test();
    }

}
