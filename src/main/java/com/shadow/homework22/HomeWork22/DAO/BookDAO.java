package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, book.author_id);
            statement.setString(2, book.title);
            statement.setInt(3, book.year_published);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Failed insert book");
            }
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed insert book");
        }
    }
}
