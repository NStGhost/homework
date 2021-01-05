package com.shadow.homework22.TestHomeWork22.Interface;

import com.shadow.homework22.TestHomeWork22.Entity.Author;
import com.shadow.homework22.TestHomeWork22.Entity.Book;

import java.util.List;

public interface IAuthorService {

    public Author findAuthorById(int id);

    public Author findAuthor(Author author);

    public void saveAuthor(Author author);

    public void deleteAuthor(Author author);

    public void updateAuthor(Author author);

    public List<Author> findAllAuthor();

}
