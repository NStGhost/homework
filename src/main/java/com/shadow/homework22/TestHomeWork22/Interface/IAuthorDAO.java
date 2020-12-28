package com.shadow.homework22.TestHomeWork22.Interface;

import com.shadow.homework22.TestHomeWork22.Entity.Author;
import com.shadow.homework22.TestHomeWork22.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface IAuthorDAO {

    public Author findById(int id);

    public void save(Author author);

    public void update(Author author);

    public void delete(Author author);

    public List<Author> findAll();
}
