package com.kubrafelek.homework04.service;

import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.exceptions.StudentNumberForOneCourseExceededException;
import com.kubrafelek.homework04.mappers.StudentMapper;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Student;
import com.kubrafelek.homework04.repository.CourseRepository;
import com.kubrafelek.homework04.repository.StudentRepository;
import com.kubrafelek.homework04.util.ErrorMessageConstants;
import com.kubrafelek.homework04.util.StudentValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public Optional<Student> saveStudent(StudentDTO studentDTO) {
        //Check the student age is valid for this function
        this.validateRequest(studentDTO);

        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        if (courseRepository.countOfStudentsInCourse() > 20) {
            throw new StudentNumberForOneCourseExceededException(ErrorMessageConstants.STUDENT_COUNT);
        }
        return Optional.of(studentRepository.save(student));
    }

    private void validateRequest(StudentDTO studentDTO) {
        StudentValidatorUtil.validateStudentAge(studentDTO.calculateStudentAge());
    }
}
