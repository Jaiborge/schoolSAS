package com.flitx.scool.service;

import com.flitx.scool.model.Transaction;
import com.flitx.scool.repo.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
@Transactional
public class TransactionService {
    public final TransactionRepository trxRepo;


    public Transaction registTrx(Transaction trx){
        return this.trxRepo.insert(trx);
    }



}
