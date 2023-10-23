package org.practice.task2_2.hibertask;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user", schema = "public")
public class UserHiber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_id_seq_generator")
    @SequenceGenerator(name = "t_user_id_seq_generator", sequenceName = "t_user_id_seq", allocationSize = 1)
    private Long id;
    private Integer age;
    private String name;
    private String email;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
}
