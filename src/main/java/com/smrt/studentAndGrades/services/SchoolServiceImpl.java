package com.smrt.studentAndGrades.services;

import com.smrt.studentAndGrades.beans.Student;
import com.smrt.studentAndGrades.excpetion.SchoolSystemException;
import com.smrt.studentAndGrades.repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final StudentRepo studentRepo;

    @Override
    public void addStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void updateStudent(Student student) throws SchoolSystemException {
        if (!studentRepo.existsById(student.getId())) {
            throw new SchoolSystemException("Id not found");
        }
        studentRepo.save(student);
    }

    @Override
    public void deleteStudentById(long studentId) throws SchoolSystemException {
        if (!studentRepo.existsById(studentId)) {
            throw new SchoolSystemException("Id not found");
        }
        studentRepo.deleteById(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(long studentId) throws SchoolSystemException {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isEmpty()) {
            throw new SchoolSystemException("Id not found");
        }
        return student.get();
    }

    @Override
    public List<Student> getAllStudentsByName(String name) {
        return studentRepo.findByNameIgnoreCase(name);
    }

    @Override
    public double getAverageScoreByStudentId(long studentId) throws SchoolSystemException {
        return getStudentById(studentId).getGrades().stream().mapToDouble(grade -> grade.getScore()).average().getAsDouble();
    }
}
