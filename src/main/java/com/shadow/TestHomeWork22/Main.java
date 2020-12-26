package com.shadow.TestHomeWork22;

import com.shadow.util.HibernateUtil;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final HibernateUtil hibernateUtil = new HibernateUtil();
        AuthorService userService = new AuthorService(hibernateUtil.getSessionFactory());

        Author author = new Author("Stiven King",85);
        userService.saveAuthor(author);

        Book book = new Book("Shine", 1965);
        book.setAuthors(author);

        Book book1 = new Book("Green mile", 1978);
        book1.setAuthors(author);

        Book book2 = new Book("Book3", 1985);
        book1.setAuthors(author);

        //Book book3 = new Book("Book4", 1997);
        //book1.setAuthors(author);



        author.addBook(book);
        author.addBook(book1);
        author.addBook(book2);
        //author.addBook(book3);

        userService.updateAuthor(author);

        System.out.println(userService.findBookById(2).toString());


        hibernateUtil.getSessionFactory().close();

    }
}
