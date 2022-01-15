package com.flitx.scool.controller;


import com.flitx.scool.model.Response;
import com.flitx.scool.model.Student;
import com.flitx.scool.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService stdService;


    @GetMapping("/list")
    public ResponseEntity<Response> getAllStudents(){

        List<Student> students= stdService.getAllStudentsBySchool();


        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Students",students))
                        .message("Students retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );



    }


}
