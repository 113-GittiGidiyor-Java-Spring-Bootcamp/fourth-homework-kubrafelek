package com.kubrafelek.homework04.mappers;

import com.kubrafelek.homework04.dto.CourseDTO;
import com.kubrafelek.homework04.dto.StudentDTO;
import com.kubrafelek.homework04.model.Course;
import com.kubrafelek.homework04.model.Student;
import com.kubrafelek.homework04.service.CourseService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);

}
