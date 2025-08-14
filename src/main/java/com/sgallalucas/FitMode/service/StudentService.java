package com.sgallalucas.FitMode.service;

import com.sgallalucas.FitMode.model.Student;
import com.sgallalucas.FitMode.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Student save(Student student) {
        return repository.save(student);
    }

    public Optional<Student> findById(String id) {
        var studentId = UUID.fromString(id);
        Optional<Student> student = repository.findById(studentId);
        return student;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void delete(Student student) {
        repository.delete(student);
    }

    public void update(Student student) {
        repository.save(student);
    }
}
