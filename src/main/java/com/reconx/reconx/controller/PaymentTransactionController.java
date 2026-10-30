package com.reconx.reconx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reconx.reconx.dto.CreatePaymentRequest;
import com.reconx.reconx.entity.PaymentTransaction;
import com.reconx.reconx.exception.DuplicatePaymentException;
import com.reconx.reconx.service.PaymentTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentTransactionController {

    private final PaymentTransactionService paymentService;

    public PaymentTransactionController(PaymentTransactionService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentTransaction createPayment(
            @Valid @RequestBody CreatePaymentRequest request) {

        return paymentService.savePayment(request);
    }
    
    @GetMapping
    public List<PaymentTransaction> getAllPayments() {
        return paymentService.getAllPayments();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {

        Optional<PaymentTransaction> payment =
                paymentService.getPaymentById(id);

        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        }

        return ResponseEntity.status(404).body("Payment not found with ID: " + id);
    }
    
    @GetMapping("/reference/{reference}")
    public ResponseEntity<?> getPaymentByReference(@PathVariable String reference) {

        Optional<PaymentTransaction> payment =
                paymentService.getPaymentByReference(reference);

        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        }

        return ResponseEntity.status(404)
                .body("Payment not found with reference: " + reference);
    }
    
    
    @ExceptionHandler(DuplicatePaymentException.class)
    public ResponseEntity<String> handleDuplicatePayment(
            DuplicatePaymentException ex) {

        return ResponseEntity.status(409).body(ex.getMessage());
    }
}
