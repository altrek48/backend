package dev.vorstu.repositories;

import dev.vorstu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    Boolean checkAvailableUsername(@Param("username") String username);

}
