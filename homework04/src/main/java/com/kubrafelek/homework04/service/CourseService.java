package com.kubrafelek.homework04.service;

import com.kubrafelek.homework04.dto.CourseDTO;
import com.kubrafelek.homework04.dto.InstructorDTO;
import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.exceptions.InstructorIsAlreadyExistException;
import com.kubrafelek.homework04.exceptions.StudentNumberForOneCourseExceededException;
import com.kubrafelek.homework04.mappers.CourseMapper;
import com.kubrafelek.homework04.mappers.StudentMapper;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Instructor;
import com.kubrafelek.homework04.model.Student;
import com.kubrafelek.homework04.repository.CourseRepository;
import com.kubrafelek.homework04.repository.StudentRepository;
import com.kubrafelek.homework04.util.ErrorMessageConstants;
import com.kubrafelek.homework04.util.StudentValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
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

    @Transactional
    public Optional<Course> saveStudentToCourse(long studentId, int courseCode) {

        Student student = findStudentById(studentId);

        Course course = findCourseByCourseCode(courseCode);
        course.getStudentList().add(student);

        if (course.getStudentList().size() > 20) {
            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.STUDENT_COUNT);
        }
        return Optional.of(courseRepository.save(course));
    }

    @Transactional
    public Course findCourseByCourseCode(int courseCode) {
        return courseRepository.findCourseByCourseCode(courseCode);
    }

    @Transactional
    public Student findStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();
        Iterable<Course> courseIterable = courseRepository.findAll();
        courseIterable.iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Transactional
    public Course findCourseById(long id) {
        return courseRepository.findById(id).get();
    }

    @Transactional
    public Course update(Course course, long id) {
        Course findCourse = findCourseById(id);
        findCourse.setCourseName(course.getCourseName());
        findCourse.setCourseCode(course.getCourseCode());
        findCourse.setCreditScore(course.getCreditScore());
        return courseRepository.save(findCourse);
    }

    @Transactional
    public String deleteById(long id) {
        courseRepository.deleteById(id);
        return "Course id => " + id + " Deleted....";
    }
}
