package com.shadow.homework22.TestHomeWork22.Service;

import com.shadow.homework22.TestHomeWork22.DAO.AuthorDAO;
import com.shadow.homework22.TestHomeWork22.DAO.BookDAO;
import com.shadow.homework22.TestHomeWork22.Entity.Book;
import com.shadow.homework22.TestHomeWork22.Interface.IBookService;
import org.hibernate.SessionFactory;

public class BookService implements IBookService {

    private final BookDAO bookDAO;
    private final SessionFactory sessionFactory;

    public BookService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        bookDAO = new BookDAO(sessionFactory);
    }

    @Override
    public Book findBookById(int id) {
        return bookDAO.findBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        deleteBook(book);
    }

}
