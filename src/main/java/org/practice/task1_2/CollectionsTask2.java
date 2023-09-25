package org.practice.task1_2;

import java.util.HashMap;
import java.util.Map;

public class CollectionsTask2 {

    public static void main(String[] args) {
        // Var 1
        Map<Integer, Integer> hashMapFirst = new HashMap<>();
        for (int i = 0; i < 10; i += 2) {
            hashMapFirst.put(i, i + 1);
        }
        for (Integer i : hashMapFirst.keySet()) {
            System.out.println(i + ":  " + hashMapFirst.get(i));
        }
        System.out.println();
        for (int i = 0; i < 10; i += 2) {
            hashMapFirst.put(i + 1, i);
            hashMapFirst.remove(i);
        }
        for (Integer i : hashMapFirst.keySet()) {
            System.out.println(i + ":  " + hashMapFirst.get(i));
        }
        System.out.println();
        System.out.println();


        // Var 2
        Map<Integer, String> hashMapSecond = new HashMap<>();
        for (int i = 0; i < 10; i += 2) {
            hashMapSecond.put(i, "a" + i);
        }
        for (Integer i : hashMapSecond.keySet()) {
            System.out.println(i + ":  " + hashMapSecond.get(i));
        }
        System.out.println();

        Map<String, Integer> hashMapSecondSwapped = new HashMap<>();
        for (Integer i : hashMapSecond.keySet()) {
            hashMapSecondSwapped.put(hashMapSecond.get(i), i);
        }
        for (String s : hashMapSecondSwapped.keySet()) {
            System.out.println(s + ":  " + hashMapSecondSwapped.get(s));
        }
    }

}
