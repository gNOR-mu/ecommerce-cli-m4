package com.ecommerce.demo.repository.impl;

import com.ecommerce.demo.model.Payment;
import com.ecommerce.demo.repository.PaymentRepository;

public class InMemoryPaymentRepository extends InMemoryAbstractRepository<Payment, Long> implements PaymentRepository {
}
