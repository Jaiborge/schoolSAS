package com.flitx.scool.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@Data
@Document("Student")
public class Student {

    @Id
    private String reference;

    private String name;

    private List<Order> orders;

    //@Indexed(unique = true)



    public Student( String name, String reference) {
        super();
        this.name = name;
        this.reference=reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
