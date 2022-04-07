package com.smrt.studentAndGrades.controllers;

import com.smrt.studentAndGrades.beans.Student;
import com.smrt.studentAndGrades.excpetion.SchoolSystemException;
import com.smrt.studentAndGrades.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping("addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        schoolService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) throws SchoolSystemException {
        schoolService.updateStudent(student);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteStudent/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable long studentId) throws SchoolSystemException {
        schoolService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("allStudents")
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(schoolService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable long studentId) throws SchoolSystemException {
        return new ResponseEntity<>(schoolService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping("studentsByName/{name}")
    public ResponseEntity<?> getStudentsByName(@PathVariable String name) {
        return new ResponseEntity<>(schoolService.getAllStudentsByName(name), HttpStatus.OK);
    }

    @GetMapping("averageByStudentId/{studentId}")
    public ResponseEntity<?> getAverageScoreByStudentId(@PathVariable long studentId) throws SchoolSystemException {
        return new ResponseEntity<>(schoolService.getAverageScoreByStudentId(studentId), HttpStatus.OK);
    }
}
