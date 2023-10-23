package org.practice.task2_2.jdbctask;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJdbc {

    private Long id;
    private Integer age;
    private String name;
    private String email;
    private LocalDate registrationDate;
}
