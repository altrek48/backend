package dev.vorstu.repositories;

import dev.vorstu.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM UserEntity u WHERE LOWER(u.username) = LOWER(:username)")
    Boolean checkAvailableUsername(@Param("username") String username);

}
