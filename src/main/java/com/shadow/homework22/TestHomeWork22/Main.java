package com.shadow.homework22.TestHomeWork22;

import com.shadow.homework22.TestHomeWork22.Entity.Author;
import com.shadow.homework22.TestHomeWork22.Entity.Book;
import com.shadow.homework22.TestHomeWork22.Service.AuthorService;
import com.shadow.homework22.TestHomeWork22.Service.BookService;
import com.shadow.homework22.TestHomeWork22.Util.HibernateUtil;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final HibernateUtil hibernateUtil = new HibernateUtil();

        AuthorService authorService = new AuthorService(hibernateUtil.getSessionFactory());

        Author author = new Author("Stiven King",85);
        authorService.saveAuthor(author);

        Book book = new Book("Shine", 1965);
        book.setAuthors(author);

        Book book1 = new Book("Green mile", 1978);
        book1.setAuthors(author);

        Book book2 = new Book("Book3", 1985);
        book1.setAuthors(author);

        author.addBook(book);
        author.addBook(book1);
        author.addBook(book2);

        authorService.updateAuthor(author);

        BookService bookService = new BookService(hibernateUtil.getSessionFactory());
        Book temp = bookService.findBookById(10);
        if (temp != null) System.out.println(temp.toString());


        hibernateUtil.getSessionFactory().close();

    }
}
