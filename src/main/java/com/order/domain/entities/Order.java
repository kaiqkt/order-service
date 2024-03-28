package com.order.domain.entities;

import io.azam.ulidj.ULID;

import java.util.List;
import java.util.Objects;

public class Order {
    private final String id;
    private final String buyerId;
    private final List<Product> products;
    private Status status;

    public Order(String buyerId, List<Product> products) {
        this.id = ULID.random();
        this.buyerId = buyerId;
        this.products = products;
        this.status = Status.DRAFT;
    }

    public String getId() {
        return id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(buyerId, order.buyerId) && Objects.equals(products, order.products) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyerId, products, status);
    }
}
