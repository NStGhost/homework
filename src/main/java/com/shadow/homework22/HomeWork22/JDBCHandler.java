package com.shadow.homework22.HomeWork22;

import com.shadow.homework22.HomeWork22.DAO.AuthorDAO;
import com.shadow.homework22.HomeWork22.DAO.BookDAO;
import com.shadow.homework22.HomeWork22.DAO.CommentDAO;
import com.shadow.homework22.HomeWork22.DAO.UserDAO;
import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;
import com.shadow.homework22.HomeWork22.Models.Comment;
import com.shadow.homework22.HomeWork22.Models.User;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class JDBCHandler implements IBookRepository{

    private final static int INVALID_ID = 0;

    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;


    public JDBCHandler(Connection connection) {
        this.authorDAO = new AuthorDAO(connection);
        this.bookDAO = new BookDAO(connection);
        this.commentDAO = new CommentDAO(connection);
        this.userDAO = new UserDAO(connection);

        /*

        String expr = "SELECT books.title, authors.name" +
                " FROM books" +
                " JOIN authors_books ON books.id = authors_books.book_id" +
                " JOIN authors ON authors.id = authors_books.author_id" +
                " WHERE books.publish_year > 2000";

         */
    }


    @Override
    public Collection<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();

    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public Collection<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorDAO.getAuthorById(id);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Optional<Comment> getCommentById(int id) {
        return commentDAO.getCommentById(id);
    }

    @Override
    public Optional<Book> getBookByNameAndAuthor(String titleBook, String authorName) {
        return bookDAO.getBookByNameAndAuthor(titleBook, authorName);
    }

    @Override
    public void save(Book book, Author author) {
        if (author.id == INVALID_ID) {
            Optional<Author> tempAuthor = authorDAO.getAuthorByParams(author);
            if (tempAuthor.isPresent()) {
                author.id = tempAuthor.get().id;
            } else {
                author.id = authorDAO.insertAuthor(author);
            }
        } else {
            authorDAO.updateAuthor(author);
        }
        book.author_id = author.id;
        if (book.id == INVALID_ID) {
            Optional<Book> matchingBook = bookDAO.getBookByNameAndAuthor(book.title, author.name);
            if (matchingBook.isPresent()) {
                book.id = matchingBook.get().id;
                bookDAO.updateBook(book);
            } else {
                bookDAO.insertBook(book);
            }
        }
    }

    @Override
    public void save(Author author) {
        if (author.id == INVALID_ID) {
            Optional<Author> tempAuthor = authorDAO.getAuthorByParams(author);
            if (!tempAuthor.isPresent()) {
                authorDAO.insertAuthor(author);
            } else {
                System.out.println("Author is exits");
            }
        } else {
            authorDAO.updateAuthor(author);
        }
    }

    @Override
    public void save(Book book) {
        if (book.id == INVALID_ID) {
            Optional<Book> matchingBook = bookDAO.getBookByNameAndAuthorID(book.title, book.author_id);
            if (matchingBook.isPresent()) {
                book.id = matchingBook.get().id;
                bookDAO.updateBook(book);
            } else {
                bookDAO.insertBook(book);
            }
        } else {
            bookDAO.updateBook(book);
        }
    }

    @Override
    public void save(User user) {
        if (user.id == INVALID_ID) {
            Optional<User> tempUser = userDAO.getUserByEmail(user.email);
            if (!tempUser.isPresent()) {
                userDAO.insertUser(user);
            } else {
                System.out.println("User is exits");
            }
        } else {
            userDAO.updateUser(user);
        }
    }

    @Override
    public void save(Comment comment) {
        if (comment.id == INVALID_ID) {
            Optional<Comment> tempComment = commentDAO.getCommentByParams(comment);
            if (!tempComment.isPresent()) {
                commentDAO.insertComment(comment);
            } else {
                System.out.println("Comment is exits");
            }

        } else {
            commentDAO.updateComment(comment);
        }
    }

    @Override
    public void deleteAuthor(int id) {
        authorDAO.delete(id);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.delete(id);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void deleteComment(int id) {
        commentDAO.delete(id);
    }
}
