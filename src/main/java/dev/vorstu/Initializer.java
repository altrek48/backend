package dev.vorstu;

import dev.vorstu.dto.Student;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    StudentRepository studentRepository;

    public void initial() {
        studentRepository.save(new Student("Andry", "Amirov", "VM", "durak", "alalalala"));
        studentRepository.save(new Student("Denis", "Ignatov", "AM", "debil", "ululululu"));
        studentRepository.save(new Student("Vova", "Redisov", "IM", "lox", "elelelele"));
    }

}
