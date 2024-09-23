package dev.vorstu;

import dev.vorstu.entities.Password;
import dev.vorstu.entities.Role;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public void initial() {

        UserEntity student = new UserEntity(
                null,
                "student",
                Role.STUDENT,
                new Password("1234"),
                true
        );

        UserEntity studentAdmin = new UserEntity(
                null,
                "studentAdmin",
                Role.ADMIN,
                new Password("12345"),
                true
        );

        userRepository.save(student);
        userRepository.save(studentAdmin);

        studentRepository.save(new StudentEntity("Andry", "Amirov", "VM", "durak", "alalalala"));
        studentRepository.save(new StudentEntity("Denis", "Ignatov", "AM", "debil", "ululululu"));
        studentRepository.save(new StudentEntity("Vova", "Redisov", "IM", "lox", "elelelele"));
    }

}
