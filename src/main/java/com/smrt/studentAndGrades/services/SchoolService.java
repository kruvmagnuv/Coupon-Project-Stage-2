package com.smrt.studentAndGrades.services;

import com.smrt.studentAndGrades.beans.Student;
import com.smrt.studentAndGrades.excpetion.SchoolSystemException;

import java.util.List;

public interface SchoolService {
    void addStudent(Student student);

    void updateStudent(Student student) throws SchoolSystemException;

    void deleteStudentById(long studentId) throws SchoolSystemException;

    List<Student> getAllStudents();

    Student getStudentById(long studentId) throws SchoolSystemException;

    List<Student> getAllStudentsByName(String name);

    double getAverageScoreByStudentId(long studentId) throws SchoolSystemException;
}
