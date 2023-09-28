package org.practice.task1_6;

import java.io.*;

public class SerializationTask {

    public static void main(String[] args) {
        SerializationTask task = new SerializationTask();
        task.serializePersons();
        task.deserializePersons();
    }

    private void serializePersons() {
        Person[] persons = {
                new Person("Никита", 1),
                new Person("Егор", 7),
                new Person("Владислав", 20),
                new Person("Александр", 30),
                new Person("Николай", 70)
        };
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save.ser"))) {
            for (Person person : persons) {
                objectOutputStream.writeObject(person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deserializePersons() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.ser"))) {
            readPersonsAndPrintToConsole(objectInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readPersonsAndPrintToConsole(ObjectInputStream objectInputStream) {
        while (true) {
            Person person;
            try {
                person = (Person) objectInputStream.readObject();
            } catch (EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println(person);
        }
    }
}
