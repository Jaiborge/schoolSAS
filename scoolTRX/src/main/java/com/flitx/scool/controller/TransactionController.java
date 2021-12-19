package com.flitx.scool.controller;


import com.flitx.scool.model.Transaction;
import com.flitx.scool.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/trx")
public class TransactionController {

    private final TransactionService trxService;


/*
    @GetMapping("/findTrx/{id}")
    public ResponseEntity<Response> getAllTrxs(@PathVariable("id") String reference) {


        System.out.println("Reference:" + reference);
        Student student = stdService.getStudentByReference(reference);
        System.out.println("student:" + student.getName());


        List<Order> orders = stdService.getAllOrdersByStudentRef(reference);



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


 */



    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Transaction> create( @RequestBody Transaction trx) {
         //Transaction trxCreated = new Transaction(trx);
        trxService.registTrx(trx);
        return new ResponseEntity(trx, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/processTrx", method = RequestMethod.POST)
    public ResponseEntity<Transaction> processTrx( @RequestBody Transaction trx) {
        //Transaction trxCreated = new Transaction(trx);
        trxService.registTrx(trx);
        return new ResponseEntity(trx, HttpStatus.CREATED);
    }

}
