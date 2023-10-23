package org.practice.task2_2.hibertask;

import org.practice.task2_2.Dao;

public class HiberTask {

    public static void main(String[] args) {
        Dao<UserHiber> userDaoHiber = new UserDaoHiberImpl();
        Dao<UserHiber> userDaoCrit = new UserDaoCritImpl();

        int selectedPage = 1;
        for (UserHiber userHiber : userDaoHiber.getAll(selectedPage)) {
            System.out.println(userHiber);
        }
        if (userDaoHiber.getAll(selectedPage).isEmpty()) {
            System.out.println("list is empty");
        }
        selectedPage = 3;
        for (UserHiber userHiber : userDaoCrit.getAll(selectedPage)) {
            System.out.println(userHiber);
        }
        if (userDaoCrit.getAll(selectedPage).isEmpty()) {
            System.out.println("list is empty");
        }
    }

}
