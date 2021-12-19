package com.flitx.scool.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Document("Transaction")
public class Transaction {

    private String id;
    private String transactionId;
    private String cardType;
    private String bank;
    private String authCode;
    private String last4Digits;
    private LocalDateTime transDate;
    private String cardNature;
    private BigDecimal amount;
    private TransStatus status;


    public Transaction(String transactionId, String cardType, String bank, String authCode, String last4Digits, LocalDateTime transDate, String cardNature, BigDecimal amount, TransStatus status) {
        this.transactionId = transactionId;
        this.cardType = cardType;
        this.bank = bank;
        this.authCode = authCode;
        this.last4Digits = last4Digits;
        this.transDate = transDate;
        this.cardNature = cardNature;
        this.amount = amount;
        this.status = status;
    }

    public  Transaction (Transaction trx) {

        this.transactionId = trx.getTransactionId();
        this.cardType =  trx.getCardType();
        this.bank = trx.getBank();
        this.authCode = trx.getAuthCode();
        this.last4Digits = trx.getLast4Digits();
        this.transDate = trx.getTransDate();
        this.cardNature = trx.getCardNature();
        this.amount = trx.getAmount();
        this.status = trx.getStatus();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getLast4Digits() {
        return last4Digits;
    }

    public void setLast4Digits(String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public LocalDateTime getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDateTime transDate) {
        this.transDate = transDate;
    }

    public String getCardNature() {
        return cardNature;
    }

    public void setCardNature(String cardNature) {
        this.cardNature = cardNature;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransStatus getStatus() {
        return status;
    }

    public void setStatus(TransStatus status) {
        this.status = status;
    }
}
