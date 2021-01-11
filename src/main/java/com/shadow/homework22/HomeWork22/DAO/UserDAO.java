package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void delete(int id) {
        final String temp = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            int a = statement.executeUpdate();
            if (a != -1) {
                throw new RuntimeException("Failed delete user");
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed delete user");
        }
    }

    public Optional<User> getUserById(int id) {
        final String temp = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(createUserFromResultSet(resultSet));
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get user from id");
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        final User user = new User();
        user.id = resultSet.getInt("id");
        user.name = resultSet.getString("name");
        user.year = resultSet.getInt("year");
        return user;
    }

    public int insertUser(User user) {
        final String temp = "INSERT INTO users(name, year) VALUES(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setString(1, user.name);
            statement.setInt(2, user.year);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Failed insert user");
            }
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed insert user");
        }
    }

}
