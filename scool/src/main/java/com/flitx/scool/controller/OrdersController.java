package com.flitx.scool.controller;


import com.flitx.scool.api.PayTransaction;
import com.flitx.scool.model.Order;
import com.flitx.scool.model.Response;
import com.flitx.scool.model.Student;
import com.flitx.scool.model.Transaction;
import com.flitx.scool.service.OrderService;
import com.flitx.scool.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/orders")
public class OrdersController {

    private final StudentService stdService;
    private final OrderService orderService;
    @Autowired
    private final PayTransaction paytrx;


    @GetMapping("/findOrdersByRef/{id}")
    public ResponseEntity<Response> getAllOrders(@PathVariable("id") String reference) {


        System.out.println("Reference:" + reference);

        Student student = stdService.getStudentByReference(reference);//.getOrdersByStudentRef(reference);
        System.out.println("student:" + student.getName());


        List<Order> orders = stdService.getAllOrdersByStudentRef(reference);

      //  return new ResponseEntity<>(orders, HttpStatus.OK);

        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Orders",orders))
                        .message("Orders retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/findPendingOrdersByRef/{id}")
    public ResponseEntity<Response> getPendingOrders(@PathVariable("id") String reference) {


        System.out.println("Reference:" + reference);
        Student student = stdService.getStudentByReference(reference);//.getOrdersByStudentRef(reference);
        System.out.println("student:" + student.getName());


        List<Order> orders = stdService.getOrdersPendingByStudentRef(reference);

        //  return new ResponseEntity<>(orders, HttpStatus.OK);

        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Orders",orders))
                        .message("Orders retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/findPayedOrdersByRef/{id}")
    public ResponseEntity<Response> getPayedOrders(@PathVariable("id") String reference) {


        System.out.println("Reference:" + reference);
        Student student = stdService.getStudentByReference(reference);//.getOrdersByStudentRef(reference);
        System.out.println("student:" + student.getName());


        List<Order> orders = stdService.getPayedOrdersByStudentRef(reference);

        //  return new ResponseEntity<>(orders, HttpStatus.OK);

        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Orders",orders))
                        .message("Orders retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllOrders() {


      System.out.println("Geting All orders");

        //  return new ResponseEntity<>(orders, HttpStatus.OK);

        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Orders",orderService.findAllOrders()))
                        .message("Orders retrived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @PostMapping("/PayOrders")
    public void  payOrders( @RequestBody Transaction trx) {

        ResponseEntity<Response> res =  paytrx.processTrx(trx);

        System.out.println("Response:"+res.getStatusCode());

    }


}
