package com.shadow.homework22.HomeWork22;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void run() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/test123?characterEncoding=UTF-8&useUnicode=true&serverTimezone=Europe/Moscow";
        final String user = "root";
        final String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            hadlerConnection(connection);
        }

    }

    private void hadlerConnection(Connection connection) {
        JDBCHandler jdbcHandler = new JDBCHandler(connection);

        Optional<Book> book = jdbcHandler.getBookById(2);
        if (book.isPresent()) {
            Optional<Author> author = jdbcHandler.getAuthorById(book.get().author_id);
            author.ifPresent(author1 ->
                    book.ifPresent(
                            value ->
                                    System.out.printf("%d %s %s %d%n", value.id, value.title, author1.name, value.year_published)
                    ));
        }


        /*
        createAuthor(jdbcHandler);
        createBooks(jdbcHandler);
        */



    }

    private void createBooks(JDBCHandler jdbcHandler) {
        Book book = new Book(17,"Green mile",1966);
        Book book1 = new Book(17,"Shine",1970);
        Book book2 = new Book(17,"ONO",1973);

        Book book3 = new Book(18,"Dubrovsky",1973);
        Book book4 = new Book(18,"Pikovaya dama",1973);

        Book book5 = new Book(19,"Kill in west express",1973);
        Book book6 = new Book(19,"Evil and sun",1973);

        jdbcHandler.save(book);
        jdbcHandler.save(book1);
        jdbcHandler.save(book2);
        jdbcHandler.save(book3);
        jdbcHandler.save(book4);
        jdbcHandler.save(book5);
        jdbcHandler.save(book6);
    }

    private void createAuthor(JDBCHandler jdbcHandler) {
        Author author = new Author("Stiven King",1925);
        Author author1 = new Author("Pushkin",1933);
        Author author2 = new Author("Agata Kristi",1890);

        jdbcHandler.save(author);
        jdbcHandler.save(author1);
        jdbcHandler.save(author2);
    }

}
