package com.shadow.homework22.HomeWork22;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;
import com.shadow.homework22.HomeWork22.Models.Comment;
import com.shadow.homework22.HomeWork22.Models.User;

import java.util.Collection;
import java.util.Optional;

public interface IBookRepository {

    Collection<Author> getAllAuthors();
    Collection<Book> getAllBooks();
    Collection<User> getAllUsers();
    Collection<Comment> getAllComments();

    Optional<Author> getAuthorById(int id);
    Optional<Book> getBookById(int id);
    Optional<User> getUserById(int id);
    Optional<Comment> getCommentById(int id);

    Optional<Book> getBookByNameAndAuthor(String titleBook, String authorName);
    //Optional<User> getUserByEmail(String email);

    void save(Book book, Author author);

    void save(Author author);
    void save(Book book);
    void save(User user);
    void save(Comment comment);

    void deleteAuthor(int id);
    void deleteBook(int id);
    void deleteUser(int id);
    void deleteComment(int id);

    /*void deleteAuthor(Author author);
    void deleteBook(Book book);
    void deleteUser(User user);
    void deleteComment(Comment comment);*/


}
