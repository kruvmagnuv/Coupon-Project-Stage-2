package com.smrt.studentAndGrades.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 40)
    private String name;

    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL)
    @Singular
    private List<Grade> grades;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return name + ", " + birthday + ", " + grades;
    }
}
