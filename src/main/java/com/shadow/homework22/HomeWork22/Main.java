package com.shadow.homework22.HomeWork22;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;
import com.shadow.homework22.HomeWork22.Models.Comment;
import com.shadow.homework22.HomeWork22.Models.User;

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


        testShowComment(jdbcHandler);
        /*
        testShowAuthor(jdbcHandler);
        createUsers(jdbcHandler);
        createAuthor(jdbcHandler);
        createBooks(jdbcHandler);
        createComments(jdbcHandler);
        */



    }

    private void testShowComment(JDBCHandler jdbcHandler) {
        Optional<Comment> comment = jdbcHandler.getCommentById(2);
        if (comment.isPresent()) {
            Optional<User> user = jdbcHandler.getUserById(comment.get().user_id);
            Optional<Book> book = jdbcHandler.getBookById(comment.get().book_id);
            if (user.isPresent() && book.isPresent()) {
                System.out.printf("Time: %s%nUser: %s%nText: %s%nName book: %s%n",
                        comment.get().date,
                        user.get().name,
                        comment.get().text,
                        book.get().title);
            }

        } else {
            System.out.println("Comment not found");
        }
    }



    private void testShowBook(JDBCHandler jdbcHandler) {
        Optional<Book> book = jdbcHandler.getBookByNameAndAuthor("Shine","Stiven King");
        if (book.isPresent()) {
            Optional<Author> author = jdbcHandler.getAuthorById(book.get().author_id);
            author.ifPresent(author1 ->
                    book.ifPresent(
                            value ->
                                    System.out.printf("%d %s %s %d%n", value.id, value.title, author1.name, value.year_published)
                    ));
        }
    }

    private void createComments(JDBCHandler jdbcHandler) {
        Comment comment = new Comment(2,6,"Книга полный трешак, не читайте","140120211237");
        Comment comment1 = new Comment(2,6,"Книга полный трешак, не читайте","140120211237");

        Comment comment2 = new Comment(1,1,"Книга топчик за свои деньги","140120210225");
        Comment comment3 = new Comment(2,1,"Отличная книга, советую прочитать","140120211545");

        jdbcHandler.save(comment);
        jdbcHandler.save(comment1);
        jdbcHandler.save(comment2);
        jdbcHandler.save(comment3);
    }

    private void createUsers(JDBCHandler jdbcHandler) {

        User user = new User("Alex",1985,"1@1.com");
        User user1 = new User("Bred",1994,"1@1.com");
        User user2 = new User("Melisa",1983,"ss@sdf.er");
        User user3 = new User("Diana",1999, "s@.er");

        jdbcHandler.save(user);
        jdbcHandler.save(user1);
        jdbcHandler.save(user2);
        jdbcHandler.save(user3);


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
