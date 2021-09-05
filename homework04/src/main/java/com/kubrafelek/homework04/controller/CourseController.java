package com.kubrafelek.homework04.controller;

import com.kubrafelek.homework04.dto.CourseDTO;
import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.model.*;
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

    //List all courses
    @GetMapping("/list-courses")
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity(courseService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save-student-to-course/{courseId}")
    public ResponseEntity<Course> saveStudentToCourse(@PathVariable long studentId, @RequestBody CourseDTO courseDTO) {

        Optional<Course> resultOptional = courseService.saveStudentToCourse(studentId, courseDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Get course by id
    @GetMapping("/findcourseById/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable int id) {
        return new ResponseEntity<>(courseService.findCourseById(id), HttpStatus.OK);
    }

    //Add new course
    //FIX: valid anotasyonu eklenmedi
    @PostMapping("/add-course")
    public ResponseEntity addCourse(@RequestBody CourseDTO courseDTO) {

        Optional<Course> resultOptional = courseService.saveCourse(courseDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Update exist course
    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id) {
        return new ResponseEntity<>(courseService.update(course, id), HttpStatus.ACCEPTED);
    }

    //Delete course by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Course> deleteCourseById(@PathVariable long id) {
        return new ResponseEntity(courseService.deleteById(id), HttpStatus.ACCEPTED);
    }

}
