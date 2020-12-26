package com.shadow.homeWorks.homework22;

import java.util.logging.Logger;

public class Test2 {

    private final Logger logger;

    private String messageToUser = "Enter command\n" +
            "1 - create table student\n" +
            "2 - delete table\n" +
            "3 - add student\n" +
            "4 - show all students\n" +
            "5 - search student\n" +
            "-1 - Exit";

    public Test2() {
        this.logger = Logger.getLogger("Errors");
        MySQL_Connector mySQLConnector = new MySQL_Connector(logger);
        mySQLConnector.closeConnect();
    }

    /*private ArrayList<StudentTemp> executeSelect(String query) {
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
    }*/

    public static void main(String[] args) {
        new com.shadow.homeWorks.homework22.Test2();
    }
}
