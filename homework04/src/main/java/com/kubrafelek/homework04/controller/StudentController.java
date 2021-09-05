package com.kubrafelek.homework04.controller;

import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Student;
import com.kubrafelek.homework04.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list-students")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save-student")
    public ResponseEntity<Student> saveStudentToCourse(@RequestBody StudentDTO studentDTO) {

        Optional<Student> resultOptional = studentService.saveStudent(studentDTO);

        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
