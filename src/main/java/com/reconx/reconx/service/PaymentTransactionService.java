package com.reconx.reconx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reconx.reconx.dto.CreatePaymentRequest;
import com.reconx.reconx.entity.PaymentTransaction;
import com.reconx.reconx.exception.DuplicatePaymentException;
import com.reconx.reconx.repository.PaymentTransactionRepository;

@Service
public class PaymentTransactionService {

    private final PaymentTransactionRepository paymentRepository;

    public PaymentTransactionService(PaymentTransactionRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentTransaction savePayment(CreatePaymentRequest request) {
    	
    	if (paymentRepository
    	        .findByTransactionReference(request.getTransactionReference())
    	        .isPresent()) {

    		throw new DuplicatePaymentException(
    		        "Payment already exists with reference: "
    		        + request.getTransactionReference());
    	}

        PaymentTransaction payment = new PaymentTransaction();

        payment.setTransactionReference(request.getTransactionReference());
        payment.setCustomerReference(request.getCustomerReference());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentDate(request.getPaymentDate());

        return paymentRepository.save(payment);
    }
    
    public List<PaymentTransaction> getAllPayments() {
        return paymentRepository.findAll();
    }
    
    public Optional<PaymentTransaction> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }
    
    public Optional<PaymentTransaction> getPaymentByReference(String reference) {
        return paymentRepository.findByTransactionReference(reference);
    }
}
