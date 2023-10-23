package org.practice.task2_2.jdbctask;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTask {

    public static void main(String[] args) throws IOException, SQLException {

        UserDaoJdbcImpl userDao = new UserDaoJdbcImpl();
//            UserHiber firstUser = new UserHiber(20, "UserHiber 1", "user1@example.com", LocalDate.of(2021, 12, 15));
//            userDao.create(firstUser);
//            userDao.update(userDao.getById(firstUser.getId()), new UserHiber( 21, "UserHiber 2", "user2@example.com", LocalDate.of(2022, 1, 15)));
//            userDao.delete(firstUser);

        int selectedPage = 2;
        for (UserJdbc userJdbc : userDao.getAll(selectedPage)) {
            System.out.println(userJdbc);
        }
        if (userDao.getAll(selectedPage).isEmpty()) {
            System.out.println("list is empty");
        }
    }
}

/*
new UserHiber(1, 20, "UserHiber 1", "user1@example.com", LocalDate.of(2021, 12, 15))
new UserHiber(2, 21, "UserHiber 2", "user2@example.com", LocalDate.of(2022, 01, 15))
new UserHiber(4, 23, "UserHiber 4", "user4@example.com", LocalDate.of(2022, 03, 17))
new UserHiber(3, 22, "UserHiber 3", "user3@example.com", LocalDate.of(2022, 02, 16))
new UserHiber(5, 24, "UserHiber 5", "user5@example.com", LocalDate.of(2022, 04, 18))
new UserHiber(6, 25, "UserHiber 6", "user6@example.com", LocalDate.of(2022, 05, 19))
new UserHiber(7, 26, "UserHiber 7", "user7@example.com", LocalDate.of(2022, 06, 20))
new UserHiber(8, 27, "UserHiber 8", "user8@example.com", LocalDate.of(2022, 07, 21))
new UserHiber(9, 28, "UserHiber 9", "user9@example.com", LocalDate.of(2022, 08, 22))
new UserHiber(10, 29, "UserHiber 10", "user10@example.com", LocalDate.of(2022, 09, 23))
 */
