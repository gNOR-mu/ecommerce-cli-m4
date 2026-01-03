package com.ecommerce.demo.model;

import com.ecommerce.demo.enums.PaymentsMethod;
import com.ecommerce.demo.model.base.Identifiable;

import java.time.LocalDateTime;

public class Payment implements Identifiable<Long> {
    private Long id;

    private PaymentsMethod method;
    private LocalDateTime payday;
    private boolean paid;

    public Payment(Long id, PaymentsMethod method, LocalDateTime payday, boolean paid) {
        this.id = id;
        this.method = method;
        this.payday = payday;
        this.paid = paid;
    }

    @Override
    public Long getId() {
        return id;
    }

    public PaymentsMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentsMethod method) {
        this.method = method;
    }

    public LocalDateTime getPayday() {
        return payday;
    }

    public void setPayday(LocalDateTime payday) {
        this.payday = payday;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
