package dev.vorstu;

import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    StudentRepository studentRepository;

    public void initial() {
        studentRepository.save(new StudentEntity("Andry", "Amirov", "VM", "durak", "alalalala"));
        studentRepository.save(new StudentEntity("Denis", "Ignatov", "AM", "debil", "ululululu"));
        studentRepository.save(new StudentEntity("Vova", "Redisov", "IM", "lox", "elelelele"));
    }

}
