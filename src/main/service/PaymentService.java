package main.service;

import main.model.Payment;
import main.repository.PaymentRepository;

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
}
