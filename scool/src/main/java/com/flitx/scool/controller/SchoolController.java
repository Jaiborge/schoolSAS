package com.flitx.scool.controller;


import com.flitx.scool.model.Response;
import com.flitx.scool.model.School;
import com.flitx.scool.repo.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping ("/api/v1/schools")
public class SchoolController{

    private final SchoolRepository schoolRepo;

    @Autowired
    public SchoolController (SchoolRepository schoolRepo){
        this.schoolRepo=schoolRepo;

    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAllSchools() {
        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Schools", schoolRepo.findAll()))
                        .message("Students retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}

