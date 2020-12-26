package com.shadow.TestHomeWork22;

import com.shadow.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AuthorDAO {

    private final SessionFactory sessionFactory;

    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Author findById(int id) {
        return sessionFactory.openSession().get(Author.class, id);
    }

    public void save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(author);
        tx1.commit();
        session.close();
    }

    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(author);
        tx1.commit();
        session.close();
    }

    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(author);
        tx1.commit();
        session.close();
    }

    public Book findBookById(int id) {
        return sessionFactory.openSession().get(Book.class, id);
    }

    public List<Author> findAll() {
        List<Author> users = (List<Author>)  sessionFactory.openSession().createQuery("From Author").list();
        return users;
    }
}