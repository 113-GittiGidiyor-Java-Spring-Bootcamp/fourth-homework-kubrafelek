package com.kubrafelek.homework04.controller;

import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.model.Student;
import com.kubrafelek.homework04.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save-student-to-course")
    public ResponseEntity<Student> saveStudentToCourse(@RequestBody StudentDTO studentDTO) {

        Optional<Student> resultOptional = studentService.saveStudent(studentDTO);

        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
