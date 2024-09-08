package dev.vorstu.repositories;

import dev.vorstu.dto.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("from Student")
    Page<Student> findAllByPageRequest(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(s.surname) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(s.group) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<Student> findByFilter(@Param("filter") String filter, Pageable pageable);




}
