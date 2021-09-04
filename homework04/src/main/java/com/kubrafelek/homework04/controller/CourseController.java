package com.kubrafelek.homework04.controller;

import com.kubrafelek.homework04.dto.CourseDTO;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Instructor;
import com.kubrafelek.homework04.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/list-courses")
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
    }

    //valid anotasyonu eklenmedi
    @PostMapping("/add-course")
    public ResponseEntity addCourse(@RequestBody CourseDTO courseDTO) {

        Optional<Course> resultOptional = courseService.saveCourse(courseDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
