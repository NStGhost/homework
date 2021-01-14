package com.shadow.homework22.HomeWork22.Models;

public class User {

    public int id;
    public String name;
    public int year;
    public String email;

    public User() {
    }

    public User(String name, int year, String email) {
        this.name = name;
        this.year = year;
        this.email = email;
    }
}
