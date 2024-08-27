package dev.vorstu.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    public Student() {

    };

    public Student(Long id, String name, String group, String surname, String debt, String coments) {
        this(name, surname, group, debt, coments);
        this.id = id;
    }

    public Student(String name, String surname, String group, String debt, String coments) {
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

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }

    public String getComents() {
        return coments;
    }
}
