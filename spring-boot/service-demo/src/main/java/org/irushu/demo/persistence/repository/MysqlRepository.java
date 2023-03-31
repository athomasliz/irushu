package org.irushu.demo.persistence.repository;

import org.irushu.demo.persistence.model.Mysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MysqlRepository extends JpaRepository<Mysql, String> {

    @Query("SELECT m FROM Mysql m WHERE m.input = :input")
    List<Mysql> findByInput(@Param("input") String input);
}
