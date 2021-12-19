package com.flitx.scool.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("Charge")
public class Charge {

    private String source;
    private BigDecimal amount;

    private String description;
    private String paymentMethod;

    private String currency;
    private String redirect3dsUri;

    private String cvv;
    private String saveCard;

    private Billing billing;



}
