package com.kubrafelek.homework04.service;

import com.kubrafelek.homework04.dto.CourseDTO;
import com.kubrafelek.homework04.dto.InstructorDTO;
import com.kubrafelek.homework04.exceptions.InstructorIsAlreadyExistException;
import com.kubrafelek.homework04.mappers.CourseMapper;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Instructor;
import com.kubrafelek.homework04.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public Optional<Course> saveCourse(CourseDTO courseDTO) {

        boolean isExists = courseRepository.selectExistsCourseCode(courseDTO.getCourseCode());
        if (isExists) {
            throw new InstructorIsAlreadyExistException("Course " + courseDTO.getCourseName() + " code " + courseDTO.getCourseCode() + " is already exists !");
        }

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return Optional.of(courseRepository.save(course));
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();
        Iterable<Course> courseIterable = courseRepository.findAll();
        courseIterable.iterator().forEachRemaining(courseList::add);
        return courseList;
    }
}
