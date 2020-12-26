package com.shadow.Other.OLD.Hibernate;

import com.shadow.TestHomeWork22.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();

        UserService userService = new UserService(hibernateUtil.getSessionFactory());
        User user = new User("Masha",26);
        userService.saveUser(user);
        Car ferrari = new Car("Ferrari", "red");
        ferrari.setUser(user);
        user.addAuto(ferrari);
        Car ford = new Car("Ford", "black");
        ford.setUser(user);
        user.addAuto(ford);
        userService.updateUser(user);
        //HibernateUtil.getSessionFactory().close();
    }
}
