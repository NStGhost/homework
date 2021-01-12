package com.shadow.homework22.HomeWork22.DAO;

import com.shadow.homework22.HomeWork22.Models.Author;
import com.shadow.homework22.HomeWork22.Models.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
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

    public Collection<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            final Collection<User> users = new ArrayList<>();
            ResultSet cursor = statement.executeQuery("SELECT * FROM users");
            while (cursor.next()) {
                users.add(createUserFromResultSet(cursor));
            }
            return users;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed get all users");
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

    public void updateUser(User user) {
        final String temp = "UPDATE users SET name = ?, year = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(temp)) {
            statement.setString(1, user.name);
            statement.setInt(2, user.year);
            statement.setInt(3, user.id);
            int r = statement.executeUpdate();
            if (r != 1){
                throw new IllegalArgumentException("Error update user " + r);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Error update user");
        }
    }

}
