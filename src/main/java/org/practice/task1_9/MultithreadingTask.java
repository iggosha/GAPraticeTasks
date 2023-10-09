package org.practice.task1_9;

//Реализовать механизм периодического асинхронного выполнения задач,
// который, не останавливая работу основной программы, раз в 10 секунд делает следующие действия:
//1. пишет в консоль "Асинхронный привет!"
//2. ждет (sleep) 5 секунд
//3. пишет в консоль "Асинхронный пока!"
//Основная программа при этом должна каждую секунду писать в консоль "Работает основная программа".

import java.util.concurrent.*;

public class MultithreadingTask {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Асинхронный привет!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Асинхронный пока!");
        }, 0, 10, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() ->
        {
            System.out.println("Работает основная программа");
        }, 0, 1, TimeUnit.SECONDS);
    }
}