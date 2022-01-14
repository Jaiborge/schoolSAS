package com.flitx.scool.controller;


import com.flitx.scool.model.Response;
import com.flitx.scool.model.TransStatus;
import com.flitx.scool.model.Transaction;
import com.flitx.scool.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/trx")
//@FeignClient(name="scool-transaction-ms")

public class TransactionController {



    private final TransactionService trxService;
//    @Value("${server.port}")
//    private int port;



    @RequestMapping(value = "/processTrx", method = RequestMethod.POST)
    public ResponseEntity<Response> processTrx(@RequestBody Transaction trx) {
        //Transaction trxCreated = new Transaction(trx);


        Transaction trxCreated =trxService.registTrx(trx);
        trxCreated.setStatus(TransStatus.APROBADA);

        System.out.println("Transaction:"+trxCreated.getStatus());

        return (ResponseEntity<Response>) ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("Transaction",trxCreated))
                        .message("Transaction Processed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );


      //  return new ResponseEntity(trx, HttpStatus.CREATED);
    }

}
