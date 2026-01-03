package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order implements Identifiable<Long> {
    private Long id;
    private Long customerId;
    private Long paymentId;

    LocalDateTime creationDate;
    BigDecimal total;

    public Order(Long customerId, Long paymentId, LocalDateTime creationDate, BigDecimal total) {
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.creationDate = creationDate;
        this.total = total;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
