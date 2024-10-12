package dev.vorstu;

import dev.vorstu.entities.Password;
import dev.vorstu.entities.Role;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Initializer {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public void initial() {

        UserEntity student = userRepository.save(new UserEntity(
                null,
                "student",
                Role.STUDENT,
                new Password("1234"),
                true
        ));

        UserEntity studentAdmin = userRepository.save(new UserEntity(
                null,
                "studentAdmin",
                Role.ADMIN,
                new Password("12345"),
                true
        ));

        studentRepository.save(new StudentEntity("Andry", "Amirov", "VM", "durak", "alalalala", studentAdmin));
        studentRepository.save(new StudentEntity("Denis", "Ignatov", "AM", "debil", "ululululu",student));
        studentRepository.save(new StudentEntity("Vova", "Redisov", "IM", "lox", "elelelele", student));
    }

}
