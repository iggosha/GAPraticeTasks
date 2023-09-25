package org.practice.task1_2;

import java.util.*;

public class CollectionsTask1 {

    public static List<Integer> arrayList = new ArrayList<>();
    public static List<Integer> linkedList = new LinkedList<>();
    public static Set<Integer> treeSet = new TreeSet<>();
    public static Set<Integer> hashSet = new HashSet<>();

    public static void main(String[] args) {
        CollectionsTask1 task = new CollectionsTask1();
        {
            // Примерное среднее время - 0.3, 2.3, 1.25 и 0.5 соответственно
            task.countTimeForAdd(task::addIntegersToArrayList, "добавления в arrayList 10_000_000");
            task.countTimeForAdd(task::addIntegersToLinkedList, "добавления в linkedList 10_000_000");
            task.countTimeForAdd(task::addIntegersToTreeSet, "добавления в treeSet 10_000_000");
            task.countTimeForAdd(task::addIntegersToHashSet, "добавления в hashSet 10_000_000");
        }
        System.out.println();
        {
            // Примерное среднее время - 2.3, 0.9, 0.65 и 0.125 соответственно
            task.countTimeForAdd(task::findIntegersInArrayList, "поиска в arrayList 100_000(!)");
            task.countTimeForAdd(task::findIntegersInLinkedList, "поиска в linkedList 30_000(!!)");
            task.countTimeForAdd(task::findIntegersInTreeSet, "поиска в treeSet 10_000_000");
            task.countTimeForAdd(task::findIntegersInHashSet, "поиска в hashSet 10_000_000");
        }
        System.out.println();
        {
            // Примерное среднее время - 1.3/100, 1.6/30K по индексу (!!!), 0.65 и 0.175 соответственно
            // Примерное среднее время - 1.3/100, 0.175/10КK по ссылке, 0.65 и 0.175 соответственно
            task.countTimeForAdd(task::removeIntegersFromArrayList, "удаления из arrayList 100(!!)");
            task.countTimeForAdd(task::removeIntegersFromLinkedList, "удаления из linkedList 10_000_000");
            task.countTimeForAdd(task::removeIntegersFromTreeSet, "удаления из treeSet 10_000_000");
            task.countTimeForAdd(task::removeIntegersFromHashSet, "удаления из hashSet 10_000_000");
        }
    }

    public void countTimeForAdd(Runnable method, String methodType) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        System.out.println("Время " + methodType + " элементов = " + ((endTime - startTime) / 1e9) + " секунды");
    }

    public void addIntegersToArrayList() {
        for (int i = 0; i < 10_000_000; i++) {
            arrayList.add(i);
        }
    }

    public void addIntegersToLinkedList() {
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.add(i);
        }
    }

    public void addIntegersToTreeSet() {
        for (int i = 0; i < 10_000_000; i++) {
            treeSet.add(i);
        }
    }

    public void addIntegersToHashSet() {
        for (int i = 0; i < 10_000_000; i++) {
            hashSet.add(i);
        }
    }

    public void findIntegersInArrayList() {
        for (int i = 0; i < 100_000; i++) {
//            arrayList.indexOf(i);
            arrayList.contains(i);
        }
    }

    public void findIntegersInLinkedList() {
        for (int i = 0; i < 30_000; i++) {
//            linkedList.indexOf(i);
            linkedList.contains(i);

        }
    }

    public void findIntegersInTreeSet() {
        for (int i = 0; i < 10_000_000; i++) {
            treeSet.contains(i);
        }
    }

    public void findIntegersInHashSet() {
        for (int i = 0; i < 10_000_000; i++) {
            hashSet.contains(i);
        }
    }

    public void removeIntegersFromArrayList() {
        for (int i = 0; i < 100; i++) {
            arrayList.remove((Object) i);
        }
    }

    public void removeIntegersFromLinkedList() {
        for (int i = 0; i < 10_000_000; i++) {
            linkedList.remove((Object) i);
        }
    }

    public void removeIntegersFromTreeSet() {
        for (int i = 0; i < 10_000_000; i++) {
            treeSet.remove(i);
        }
    }

    public void removeIntegersFromHashSet() {
        for (int i = 0; i < 10_000_000; i++) {
            hashSet.remove(i);
        }
    }
}