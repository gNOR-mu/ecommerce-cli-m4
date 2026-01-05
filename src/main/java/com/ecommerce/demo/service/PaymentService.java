package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Payment;
import com.ecommerce.demo.repository.PaymentRepository;

import java.util.Optional;

public class PaymentService implements ReadOnlyService<Payment, Long> {
    //TODO averiguar nombre constante
    private final PaymentRepository PAYMENT_REPOSITORY;

    public PaymentService(PaymentRepository paymentRepository) {
        this.PAYMENT_REPOSITORY = paymentRepository;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return PAYMENT_REPOSITORY.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return PAYMENT_REPOSITORY.existsById(id);
    }
}
