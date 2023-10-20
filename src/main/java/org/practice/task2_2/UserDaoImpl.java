package org.practice.task2_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User> {

    private List<User> users = new ArrayList<>();
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(long id) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getInt("age"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDate("registration_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getInt("age"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getDate("registration_date").toLocalDate()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        return users;
    }

    @Override
    public List<User> getAll(int selectedPage) {
        int pageSize = 10;
        int pageNumber = pageSize * (selectedPage - 1);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user LIMIT ? OFFSET ?;")) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, pageNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getInt("age"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getDate("registration_date").toLocalDate()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        return users;
    }

    @Override
    public List<User> getAllWithFilter(String filter) {
        String queryWithFilter = "SELECT * FROM t_user WHERE " + filter;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryWithFilter)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getLong("id"),
                                resultSet.getInt("age"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getDate("registration_date").toLocalDate()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        return users;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                "t_user(age, name, email, registration_date) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setInt(1, user.getAge());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, Date.valueOf(user.getRegistrationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) FROM t_user")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong("max"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        users.add(user);
    }

    @Override
    public void update(User userWithOldData, User userWithNewData) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_user" +
                " SET age=?, name=?, email=?, registration_date=? WHERE id=?")) {
            preparedStatement.setInt(1, userWithNewData.getAge());
            preparedStatement.setString(2, userWithNewData.getName());
            preparedStatement.setString(3, userWithNewData.getEmail());
            preparedStatement.setDate(4, Date.valueOf(userWithNewData.getRegistrationDate()));
            preparedStatement.setLong(5, userWithOldData.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        users.remove(userWithOldData);
        users.add(userWithNewData);
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_user WHERE id=?")) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("User is null, try another user");
        }
        users.remove(user);
    }
}
