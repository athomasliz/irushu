package org.irushu.demo.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="mysql")
public class Mysql {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="input", columnDefinition = "varchar(255)")
    private String input;

    @Column(name="output", columnDefinition = "varchar(255)")
    private String output;

}
