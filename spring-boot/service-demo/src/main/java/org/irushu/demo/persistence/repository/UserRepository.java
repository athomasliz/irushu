package org.irushu.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.irushu.demo.persistence.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT a FROM User a WHERE a.username = :username")
    User findFirstByUsername(@Param("username") String username);
}
