package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Payment;
import com.ecommerce.demo.repository.PaymentRepository;

import java.util.Optional;

public class PaymentService implements ReadOnlyService<Payment, Long> {
    //TODO averiguar nombre constante
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return paymentRepository.existsById(id);
    }
}
