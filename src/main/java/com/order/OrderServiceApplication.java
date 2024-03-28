package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}

//order
    //products
        //id
        //quantity
    //user id
    //search product price and if has in stock
    //create a draft order

    //confirm the draft order -> start saga