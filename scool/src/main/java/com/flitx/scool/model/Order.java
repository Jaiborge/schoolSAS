package com.flitx.scool.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("Order")
public class Order {


    //private int id;
    @Id
    private String reference;
    private BigDecimal amount;
    private String period;
    private Status status;
    private LocalDateTime paymentDate;
    private LocalDateTime creationDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreationtDate() {
        return creationDate;
    }

    public void setCreationtDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Transaction getTrx() {
        return trx;
    }

    public void setTrx(Transaction trx) {
        this.trx = trx;
    }

    private Transaction trx;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }



    public Order( String reference, BigDecimal amount, String period, Status status, LocalDateTime paymentDate) {
        super();
        this.reference = reference;
        this.status = status;
        this.amount=amount;
        this.period=period;
        this.creationDate= LocalDateTime.now();
        this.paymentDate=paymentDate;


    }
}
