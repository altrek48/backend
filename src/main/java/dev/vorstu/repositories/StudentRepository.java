package dev.vorstu.repositories;

import dev.vorstu.dto.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByGroup(String group);////////jpql, where like;
}
