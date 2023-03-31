package org.irushu.demo.persistence.model;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
