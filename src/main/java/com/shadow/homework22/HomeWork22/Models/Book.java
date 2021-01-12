package com.shadow.homework22.HomeWork22.Models;

public class Book {

    public int id;
    public int author_id;
    public String title;
    public int year_published;

    public Book() {
    }

    public Book(int id, int author_id, String title, int year_published) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.year_published = year_published;
    }
}
