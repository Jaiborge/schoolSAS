package com.flitx.scool.repo;

import com.flitx.scool.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {


  //  public Student findByReference(@Param("name") String reference);


}
