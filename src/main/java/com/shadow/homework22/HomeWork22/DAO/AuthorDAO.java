package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class AuthorDAO {
    private final Connection connection;

    private String table_name = "authors";

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void delete(int id) {
        final String temp = "DELETE FROM authors id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            int r = statement.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("Failed delete author" + r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Author> getAllAuthors() {
        try (Statement statement = connection.createStatement()) {
            final Collection<Author> authors = new ArrayList<>();
            ResultSet cursor = statement.executeQuery("SELECT * FROM users");
            while (cursor.next()) {
                authors.add(createAuthorFromResultSet(cursor));
            }
            return authors;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get all users");
        }
    }

    public Optional<Author> getAuthorById(int id) {
        final String temp = "SELECT * FROM authors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(createAuthorFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get author from id");
        }
    }

    private Author createAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        final Author author = new Author();
        author.id = resultSet.getInt("id");
        author.name = resultSet.getString("name");
        author.birth_year = resultSet.getInt("birth_year");
        return author;
    }

    public int insertAuthor(Author author) {
        final String temp = "INSERT INTO authors(name, birth_year) VALUES(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setString(1, author.name);
            statement.setInt(2, author.birth_year);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Failed insert author");
            }
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed insert author");
        }
    }


    public void updateAuthor(Author author) {
        final String temp = "UPDATE authors SET name = ?, birth_year = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setString(1, author.name);
            statement.setInt(2, author.birth_year);
            statement.setInt(3, author.id);
            int r = statement.executeUpdate();
            if (r != 1){
                throw new IllegalArgumentException("Error update author " + r);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error update author");
        }
    }

}
