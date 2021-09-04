package com.kubrafelek.homework04.util;

import com.kubrafelek.homework04.exceptions.BadRequestException;

public class StudentValidatorUtil {

    public static void validateStudentAge(int studentAge) {
        if(18 < studentAge && studentAge > 40){
            throw new BadRequestException(ErrorMessageConstants.STUDENT_AGE);
        }
    }
}
