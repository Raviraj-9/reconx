package com.reconx.reconx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reconx.reconx.entity.PaymentTransaction;

public interface PaymentTransactionRepository
        extends JpaRepository<PaymentTransaction, Long> {
	
	Optional<PaymentTransaction> findByTransactionReference(String transactionReference);
}
