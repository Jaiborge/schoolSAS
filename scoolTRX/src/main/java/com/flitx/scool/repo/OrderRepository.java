package com.flitx.scool.repo;

import com.flitx.scool.model.Order;
import com.flitx.scool.model.School;
import com.flitx.scool.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository <Order, String>{

    @Query(value = "{'employees.name': ?0}", fields = "{'employees' : 0}")
    List<Order> findOrdersByStudentReference(String empName);

}
