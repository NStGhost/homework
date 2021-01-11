package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CommentDAO {

    private final Connection connection;

    public CommentDAO(Connection connection) {
        this.connection = connection;
    }

    public void delete(int id) {
        final String temp = "DELETE FROM comments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            int a = statement.executeUpdate();
            if (a != -1) {
                throw new RuntimeException("Failed delete comment");
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed delete comment");
        }
    }

    public Optional<Comment> getCommentById(int id) {
        final String temp = "SELECT * FROM comments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(createCommentFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get comment from id");
        }
    }

    private Comment createCommentFromResultSet(ResultSet resultSet) throws SQLException {
        final Comment comment = new Comment();
        comment.id = resultSet.getInt("id");
        comment.books_id = resultSet.getInt("book_id");
        comment.user_id = resultSet.getInt("user_id");
        comment.text = resultSet.getString("text");
        comment.date = resultSet.getString("date");

        return comment;
    }

    public int insertComment(Comment comment) {
        final String temp = "INSERT INTO comments(book_id, user_id, text, date) VALUES(?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, comment.books_id);
            statement.setInt(2, comment.user_id);
            statement.setString(2, comment.text);
            statement.setString(2, comment.date);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Failed insert comment");
            }
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed insert comment");
        }
    }
}
