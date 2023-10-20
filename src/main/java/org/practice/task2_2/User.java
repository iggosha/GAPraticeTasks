package org.practice.task2_2;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;

    public User(int age, String name, String email, LocalDate registrationDate) {
        this.age = age;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    private int age;
    private String name;
    private String email;
    private LocalDate registrationDate;
}
