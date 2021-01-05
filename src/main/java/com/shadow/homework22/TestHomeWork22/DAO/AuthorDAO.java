package com.shadow.homework22.TestHomeWork22.DAO;

import com.shadow.homework22.TestHomeWork22.Entity.Author;
import com.shadow.homework22.TestHomeWork22.Interface.IAuthorDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AuthorDAO implements IAuthorDAO {

    private final SessionFactory sessionFactory;

    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Author findById(int id) {
        return sessionFactory.openSession().get(Author.class, id);
    }

    @Override
    public Author find(Author author) {
        return null;//sessionFactory.openSession().get(Author.class);
    }

    @Override
    public void save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(author);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(author);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(author);
        tx1.commit();
        session.close();
    }


    @Override
    public List<Author> findAll() {
        List<Author> authors = (List<Author>)  sessionFactory.openSession().createQuery("From Author").list();
        return authors;
    }
}