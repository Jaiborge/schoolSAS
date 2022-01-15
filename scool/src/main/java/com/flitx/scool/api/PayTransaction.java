package com.flitx.scool.api;



import com.flitx.scool.model.Response;
import com.flitx.scool.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "scooltrxms", url="http://localhost:8085" )
public interface PayTransaction {



    @PostMapping("/api/v1/trx/processTrx")

    public ResponseEntity<Response> processTrx (Transaction trx);


}
