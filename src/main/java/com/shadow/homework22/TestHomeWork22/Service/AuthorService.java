package com.shadow.homework22.TestHomeWork22.Service;

import com.shadow.homework22.TestHomeWork22.DAO.AuthorDAO;
import com.shadow.homework22.TestHomeWork22.Entity.Author;
import com.shadow.homework22.TestHomeWork22.Entity.Book;
import com.shadow.homework22.TestHomeWork22.Interface.IAuthorService;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorService implements IAuthorService {

    private final AuthorDAO autorDAO;
    private final SessionFactory sessionFactory;

    public AuthorService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        autorDAO = new AuthorDAO(sessionFactory);
    }

    @Override
    public Author findAuthor(Author author) {
        return autorDAO.find(author);
    }

    @Override
    public Author findAuthorById(int id) {
        return autorDAO.findById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        autorDAO.save(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        autorDAO.delete(author);
    }

    @Override
    public void updateAuthor(Author author) {
        autorDAO.update(author);
    }

    @Override
    public List<Author> findAllAuthor() {
        return autorDAO.findAll();
    }




}