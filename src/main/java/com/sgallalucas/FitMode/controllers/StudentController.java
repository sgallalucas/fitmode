package com.sgallalucas.FitMode.controllers;

import com.sgallalucas.FitMode.dtos.StudentDTO;
import com.sgallalucas.FitMode.mappers.StudentMapper;
import com.sgallalucas.FitMode.model.Student;
import com.sgallalucas.FitMode.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;
    private final StudentMapper mapper;

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody StudentDTO dto) {
        Student student = mapper.toEntity(dto);
        service.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        var optionalStudent = service.findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        StudentDTO dto = mapper.toDTO(optionalStudent.get());

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> listDTO = new ArrayList<>();
        List<Student> list = service.findAll();

        for (Student s : list) {
            StudentDTO dto = mapper.toDTO(s);
            listDTO.add(dto);
        }
        return ResponseEntity.ok(listDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var optionalStudent = service.findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(optionalStudent.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody StudentDTO dto) {
        var optionalStudent = service.findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var student = optionalStudent.get();

        student.setName(dto.name());
        student.setEmail(dto.email());
        service.update(student);

        return ResponseEntity.noContent().build();
    }
}
