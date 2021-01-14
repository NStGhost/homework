package com.shadow.homework22.HomeWork22.Models;


public class Comment {

    public int id;
    public int user_id;
    public int book_id;
    public String text;
    public String date;

    public Comment() {
    }

    public Comment(int user_id, int books_id, String text, String date) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = books_id;
        this.text = text;
        this.date = date;
    }
}
