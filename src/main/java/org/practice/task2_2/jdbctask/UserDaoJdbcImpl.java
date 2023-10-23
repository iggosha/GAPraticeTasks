package org.practice.task2_2.jdbctask;

import org.practice.task2_2.Dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoJdbcImpl implements Dao<UserJdbc> {

    private List<UserJdbc> userJdbcs = new ArrayList<>();
    private final Connection connection;

    public UserDaoJdbcImpl() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream("src/main/resources/props.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserJdbc getById(long id) {
        UserJdbc userJdbc = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userJdbc = new UserJdbc(
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
            System.out.println("UserHiber is null, try another userJdbc");
        }
        return userJdbc;
    }

    @Override
    public List<UserJdbc> getAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            userJdbcs = new ArrayList<>();
            while (resultSet.next()) {
                userJdbcs.add(
                        new UserJdbc(
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
            System.out.println("UserHiber is null, try another user");
        }
        return userJdbcs;
    }

    @Override
    public List<UserJdbc> getAll(int selectedPage) {
        int pageSize = 10;
        int pageNumber = pageSize * (selectedPage - 1);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user LIMIT ? OFFSET ?;")) {
            preparedStatement.setInt(1, pageSize);
            preparedStatement.setInt(2, pageNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            userJdbcs = new ArrayList<>();
            while (resultSet.next()) {
                userJdbcs.add(
                        new UserJdbc(
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
            System.out.println("UserHiber is null, try another user");
        }
        return userJdbcs;
    }

    // Делать так не стоит, но иначе будет мало фильтров и много кода, а фильтры не конкретизированы
    @Override
    public List<UserJdbc> getAllWithFilter(String filter) {
        String queryWithFilter = "SELECT * FROM t_user WHERE " + filter;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryWithFilter)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            userJdbcs = new ArrayList<>();
            while (resultSet.next()) {
                userJdbcs.add(
                        new UserJdbc(
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
            System.out.println("UserHiber is null, try another user");
        }
        return userJdbcs;
    }

    @Override
    public void create(UserJdbc userJdbc) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                "t_user(age, name, email, registration_date) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setInt(1, userJdbc.getAge());
            preparedStatement.setString(2, userJdbc.getName());
            preparedStatement.setString(3, userJdbc.getEmail());
            preparedStatement.setDate(4, Date.valueOf(userJdbc.getRegistrationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("UserHiber is null, try another userJdbc");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) FROM t_user")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userJdbc.setId(resultSet.getLong("max"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("UserHiber is null, try another userJdbc");
        }
        userJdbcs.add(userJdbc);
    }

    @Override
    public void update(UserJdbc userJdbcWithOldData, UserJdbc userJdbcWithNewData) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE t_user" +
                " SET age=?, name=?, email=?, registration_date=? WHERE id=?")) {
            preparedStatement.setInt(1, userJdbcWithNewData.getAge());
            preparedStatement.setString(2, userJdbcWithNewData.getName());
            preparedStatement.setString(3, userJdbcWithNewData.getEmail());
            preparedStatement.setDate(4, Date.valueOf(userJdbcWithNewData.getRegistrationDate()));
            preparedStatement.setLong(5, userJdbcWithOldData.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("UserHiber is null, try another user");
        }
        userJdbcs.remove(userJdbcWithOldData);
        userJdbcs.add(userJdbcWithNewData);
    }

    @Override
    public void delete(UserJdbc userJdbc) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_user WHERE id=?")) {
            preparedStatement.setLong(1, userJdbc.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("UserHiber is null, try another userJdbc");
        }
        userJdbcs.remove(userJdbc);
    }
}
