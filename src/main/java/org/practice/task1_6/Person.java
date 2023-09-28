package org.practice.task1_6;

import java.io.Serial;
import java.io.Serializable;


//- Создать список объектов типа "Человек" с полями имя, возраст и род деятельности.
// Данный список объектов должен быть сериализован в файл при помощи интерфейса Serializable,
// при этом поле род деятельности не должно сериализовываться - оно должно быть вычислимым.
// Соответственно при десериализации оно должно вычисляться (и заполняться в объектах) по следующему правилу:
// если человеку от 0 до 3 лет - он сидит дома, если человеку от 3 до 7 лет - ходит в детский сад,
// от 7 до 18 лет - учится в школе, от 17 до 23 - учится в институте, от 24 до 65 - работает, от 65 и выше - на пенсии.


public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;
    private transient String profession;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Serial
    private Object readResolve() {
        if (age > 0 && age < 3) {
            profession = "Сидит дома";
        } else if (age >= 3 && age < 7) {
            profession = "Ходит в детский сад";
        } else if (age >= 7 && age < 18) {
            profession = "Учится в школе";
        } else if (age >= 18 && age < 23) {
            profession = "Учится в институте";
        } else if (age >= 23 && age < 65) {
            profession = "Работает";
        } else if (age > 65) {
            profession = "На пенсии";
        } else {
            throw new IllegalArgumentException("Неверно задан возраст (<0)");
        }
        return this;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                ", profession=" + profession +
                '}';
    }
}