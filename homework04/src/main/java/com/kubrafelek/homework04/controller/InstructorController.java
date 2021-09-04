package com.kubrafelek.homework04.controller;

import com.kubrafelek.homework04.dto.InstructorDTO;
import com.kubrafelek.homework04.model.Instructor;
import com.kubrafelek.homework04.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/list-instructors")
    public ResponseEntity<List<Instructor>> findAll() {
        return new ResponseEntity(instructorService.findAll(), HttpStatus.OK);
    }

    //valid anotasyonu eklenmedi
    @PostMapping("/add-instructor")
    public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorDTO instructorDTO) {

        Optional<Instructor> resultOptional = instructorService.saveInstructor(instructorDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
