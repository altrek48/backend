package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    public StudentEntity(String name, String surname, String group, String debt, String coments, UserEntity user) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.debt = debt;
        this.coments = coments;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @Column(name = "group_of_students")
    private String group;

    private String debt;

    private String coments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
