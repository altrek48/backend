package dev.vorstu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
public class StudentEntity {

    public StudentEntity() {

    };

    public StudentEntity(Long id, String name, String group, String surname, String debt, String coments) {
        this(name, surname, group, debt, coments);
        this.id = id;
    }

    public StudentEntity(String name, String surname, String group, String debt, String coments) {
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

//    @Column(name = "user_id")
//    private Long userId;
}
