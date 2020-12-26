package com.shadow.TestHomeWork22;

import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorService {

    private final AuthorDAO autorDAO;
    private final SessionFactory sessionFactory;

    public AuthorService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        autorDAO = new AuthorDAO(sessionFactory);
    }

    public Author findAuthor(int id) {
        return autorDAO.findById(id);
    }

    public void saveAuthor(Author author) {
        autorDAO.save(author);
    }

    public void deleteAuthor(Author author) {
        autorDAO.delete(author);
    }

    public void updateAuthor(Author author) {
        autorDAO.update(author);
    }

    public List<Author> findAllAuthor() {
        return autorDAO.findAll();
    }

    public Book findBookById(int id) {
        return autorDAO.findBookById(id);
    }


}