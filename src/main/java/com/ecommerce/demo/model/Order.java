package com.ecommerce.demo.model;

import com.ecommerce.demo.model.base.Identifiable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order implements Identifiable<Long> {
    private Long id;

    private LocalDateTime creationDate;
    private BigDecimal total;

    public Order() {
        this.creationDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
