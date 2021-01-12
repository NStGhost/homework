package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;
import com.shadow.homework22.HomeWork22.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class BookDAO {
    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void delete(int id) {
        final String temp = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            int a = statement.executeUpdate();
            if (a != -1) {
                throw new RuntimeException("Failed delete book");
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed delete book");
        }
    }

    public Collection<Book> getAllBooks() {
        try (Statement statement = connection.createStatement()) {
            final Collection<Book> books = new ArrayList<>();
            ResultSet cursor = statement.executeQuery("SELECT * FROM users");
            while (cursor.next()) {
                books.add(createBookFromResultSet(cursor));
            }
            return books;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get all users");
        }
    }


    public Optional<Book> getBookById(int id) {
        final String temp = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(createBookFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get book from id");
        }
    }

    public Optional<Book> getBookByNameAndAuthor(String bookTitle, String authorName) {
        final String temp = "SELECT books.* FROM books" +
                        " JOIN authors ON authors.id = books.author_id" +
                        " WHERE books.title = ? and authors.name = ?" +
                        " LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setString(1, bookTitle);
            statement.setString(2, authorName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(createBookFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get book from id");
        }
    }


    private Book createBookFromResultSet(ResultSet resultSet) throws SQLException {
        final Book book = new Book();
        book.id = resultSet.getInt("id");
        book.author_id = resultSet.getInt("author_id");
        book.title = resultSet.getString("title");
        book.year_published = resultSet.getInt("year_published");
        return book;
    }

    public int insertBook(Book book) {
        final String temp = "INSERT INTO books(author_id, title, year_published) VALUES(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(temp, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, book.author_id);
            statement.setString(2, book.title);
            statement.setInt(3, book.year_published);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new RuntimeException("Failed insert book");
            }
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed insert book");
        }
    }

    public void updateBook(Book book) {
        final String temp = "UPDATE books SET author_id = ?, title = ?, year_published = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, book.author_id);
            statement.setString(2, book.title);
            statement.setInt(3, book.year_published);
            statement.setInt(4, book.id);
            int r = statement.executeUpdate();
            if (r != 1){
                throw new IllegalArgumentException("Error update book " + r);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error update book");
        }
    }
}
