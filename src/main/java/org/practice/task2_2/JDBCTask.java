package org.practice.task2_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

public class JDBCTask {


    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.loadFromXML(new FileInputStream("src/main/resources/props.xml"));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"))) {
            Dao<User> userDao = new UserDaoImpl(connection);

//            User firstUser = new User(20, "User 1", "user1@example.com", LocalDate.of(2021, 12, 15));
//            userDao.create(firstUser);
//            userDao.update(userDao.getById(firstUser.getId()), new User( 21, "User 2", "user2@example.com", LocalDate.of(2022, 1, 15)));
//            userDao.delete(firstUser);

            int selectedPage = 2;
            for (User user : userDao.getAll(selectedPage)) {
                System.out.println(user);
            }
            if (userDao.getAll(selectedPage).isEmpty()) {
                System.out.println("list is empty");
            }
        }
    }
}

/*
new User(1, 20, "User 1", "user1@example.com", LocalDate.of(2021, 12, 15))
new User(2, 21, "User 2", "user2@example.com", LocalDate.of(2022, 01, 15))
new User(4, 23, "User 4", "user4@example.com", LocalDate.of(2022, 03, 17))
new User(3, 22, "User 3", "user3@example.com", LocalDate.of(2022, 02, 16))
new User(5, 24, "User 5", "user5@example.com", LocalDate.of(2022, 04, 18))
new User(6, 25, "User 6", "user6@example.com", LocalDate.of(2022, 05, 19))
new User(7, 26, "User 7", "user7@example.com", LocalDate.of(2022, 06, 20))
new User(8, 27, "User 8", "user8@example.com", LocalDate.of(2022, 07, 21))
new User(9, 28, "User 9", "user9@example.com", LocalDate.of(2022, 08, 22))
new User(10, 29, "User 10", "user10@example.com", LocalDate.of(2022, 09, 23))
 */
