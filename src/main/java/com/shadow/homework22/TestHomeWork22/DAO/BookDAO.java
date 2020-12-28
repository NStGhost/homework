package com.shadow.homework22.TestHomeWork22.DAO;

import com.shadow.homework22.TestHomeWork22.Entity.Book;
import com.shadow.homework22.TestHomeWork22.Interface.IBookDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookDAO implements IBookDAO {

    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book findBookById(int id) {
        return sessionFactory.openSession().get(Book.class, id);
    }

    @Override
    public void addBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(book);
        transaction.commit();
        session.close();
    }

}
