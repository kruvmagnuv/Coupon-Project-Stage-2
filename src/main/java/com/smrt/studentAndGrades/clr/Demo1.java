package com.smrt.studentAndGrades.clr;

import com.smrt.studentAndGrades.beans.Grade;
import com.smrt.studentAndGrades.beans.Student;
import com.smrt.studentAndGrades.beans.Topic;
import com.smrt.studentAndGrades.excpetion.SchoolSystemException;
import com.smrt.studentAndGrades.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
public class Demo1 implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private Map<String, String> params;

    @Override
    public void run(String... args) throws Exception {
        String addStudent = "http://localhost:8080/addStudent";
        System.out.println("\nFirst, let's add some students:");
        Student roei = Student.builder()
                .name("Roei")
                .birthday(Date.valueOf(LocalDate.of(2002, 2, 2)))
                .grade(Grade.builder().score(100).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(92).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(roei);
        restTemplate.postForEntity(addStudent, roei, Student.class);

        Student shiri = Student.builder()
                .name("Shiri")
                .birthday(Date.valueOf(LocalDate.of(1995, 5, 5)))
                .grade(Grade.builder().score(95).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(shiri);
        restTemplate.postForEntity(addStudent, shiri, Student.class);

        Student tal = Student.builder()
                .name("Tal")
                .birthday(Date.valueOf(LocalDate.of(1991, 1, 1)))
                .grade(Grade.builder().score(90).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(tal);
        restTemplate.postForEntity(addStudent, tal, Student.class);

        Student matan = Student.builder()
                .name("Matan")
                .birthday(Date.valueOf(LocalDate.of(1994, 4, 4)))
                .grade(Grade.builder().score(100).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(92).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(matan);
        restTemplate.postForEntity(addStudent, matan, Student.class);

        Student tal2 = Student.builder()
                .name("Tal")
                .birthday(Date.valueOf(LocalDate.of(1999, 9, 9)))
                .grade(Grade.builder().score(70).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(80).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(75).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(tal2);
        restTemplate.postForEntity(addStudent, tal2, Student.class);

        Student tal3 = Student.builder()
                .name("Tal")
                .birthday(Date.valueOf(LocalDate.of(1997, 7, 7)))
                .grade(Grade.builder().score(60).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(60).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(65).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(tal3);
        restTemplate.postForEntity(addStudent, tal3, Student.class);

        Student dan = Student.builder()
                .name("Dan")
                .birthday(Date.valueOf(LocalDate.of(1998, 8, 8)))
                .grade(Grade.builder().score(50).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(50).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(45).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(dan);
        restTemplate.postForEntity(addStudent, dan, Student.class);


        String updateStudent = "http://localhost:8080/updateStudent";
        System.out.println("\nLet's update Roei birthday:");
        Student updatedRoei = Student.builder()
                .id(1)
                .name("Roei")
                .birthday(Date.valueOf(LocalDate.of(2002, 9, 12)))
                .grade(Grade.builder().score(100).topic(Topic.PROJECT1).build())
                .grade(Grade.builder().score(100).topic(Topic.PROJECT2).build())
                .grade(Grade.builder().score(92).topic(Topic.PROJECT3).build())
                .build();
        System.out.println(updatedRoei);
        restTemplate.put(updateStudent, updatedRoei);

        String deleteStudentById = "http://localhost:8080/deleteStudent/{studentId}";
        System.out.println("\nLet's delete Dan:");
        params = new HashMap<>();
        params.put("studentId", "7");
        restTemplate.delete(deleteStudentById, params);

        String getAllStudents = "http://localhost:8080/allStudents";
        System.out.println("\nLet's get all students:");
        ResponseEntity<Student[]> studentsJson = restTemplate.getForEntity(getAllStudents, Student[].class);
        List<Student> students = Arrays.asList(studentsJson.getBody());
        TablePrinter.print(students);

        String getStudentById = "http://localhost:8080/student/{studentId}";
        System.out.println("\nLet's get student number 2:");
        params.clear();
        params.put("studentId", "2");
        Student student = restTemplate.getForObject(getStudentById, Student.class, params);
        System.out.println(student);

        String getAllStudentsByName = "http://localhost:8080/studentsByName/{name}";
        System.out.println("\nLet's get all students named \"Tal\":");
        params.clear();
        params.put("name", "Tal");
        studentsJson = restTemplate.getForEntity(getAllStudentsByName, Student[].class, params);
        students = Arrays.asList(studentsJson.getBody());
        TablePrinter.print(students);

        String getAverageScoreByStudentId = "http://localhost:8080/averageByStudentId/{studentId}";
        System.out.println("\nLet's get the average of student number 3:");
        params.clear();
        params.put("studentId", "3");
        Double average = restTemplate.getForObject(getAverageScoreByStudentId, Double.class, params);
        System.out.println(average);
    }
}
