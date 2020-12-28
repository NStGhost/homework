package com.shadow.homework22.TestHomeWork22.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> booksList;

    public Author() {
    }

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
        booksList = new ArrayList<>();
    }

    public void addBook(Book book) {
        book.setAuthors(this);
        booksList.add(book);
    }

    public void removeAutors(Author authors) {
        booksList.remove(authors);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "com.shadow.TestVersionHomeWork22{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
