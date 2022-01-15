package com.flitx.scool.service;

import com.flitx.scool.model.Order;
import com.flitx.scool.model.Student;
import com.flitx.scool.repo.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flitx.scool.model.Status;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.domain.Example;

@AllArgsConstructor
@Service
@Transactional
public class StudentService {

    //private static final Object COLLECTION_NAME = new Object ;
    private final StudentRepository studentRepo;

    public List<Student> getAllStudentsBySchool(){
        return studentRepo.findAll();
    }


    public Student getStudentByReference(String reference){

        //Query query = query(where("reference").is(reference));



        return studentRepo.findByReference(reference);
    }


    public List<Order> getPayedOrdersByStudentRef(String reference){

        Student student= studentRepo.findByReference(reference);

        return student.getOrders().stream().filter(ord -> ord.getStatus().equals(Status.PAGADA)).collect(Collectors.toList());
    }

    public List<Order> getOrdersPendingByStudentRef(String reference){

        Student student= studentRepo.findByReference(reference);

        return student.getOrders().stream().filter(ord -> ord.getStatus().equals(Status.PENDIENTE)).collect(Collectors.toList());
    }

    public List<Order> getAllOrdersByStudentRef(String reference){

        Student student= studentRepo.findByReference(reference);

        return student.getOrders();
    }


}
