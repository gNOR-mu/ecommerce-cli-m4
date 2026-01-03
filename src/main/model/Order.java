package main.model;

import main.model.base.Identifiable;

import java.time.LocalDateTime;

public class Order implements Identifiable<Long> {
    private Long id;
    private Long customerId;
    private Long paymentId;

    LocalDateTime creationDate;
    double total;

    public Order(Long id, Long customerId, Long paymentId, LocalDateTime creationDate, double total) {
        this.id = id;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.creationDate = creationDate;
        this.total = total;
    }

    @Override
    public Long getId() {
        return id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
