package main.repository.impl;

import main.model.Payment;
import main.repository.PaymentRepository;

public class InMemoryPaymentRepository extends InMemoryAbstractRepository<Payment, Long> implements PaymentRepository {
}
