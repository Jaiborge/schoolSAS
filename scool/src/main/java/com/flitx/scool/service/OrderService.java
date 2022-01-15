package com.flitx.scool.service;


import com.flitx.scool.model.Order;
import com.flitx.scool.model.Student;
import com.flitx.scool.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class OrderService {
    public final OrderRepository orderRepo;


    public Order addOrder(Order order){
        return this.orderRepo.insert(order);
    }



    public List<Order> findAllOrders(){
        return this.orderRepo.findAll();
    }


}
