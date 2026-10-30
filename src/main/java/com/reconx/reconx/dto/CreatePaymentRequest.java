package com.reconx.reconx.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public class CreatePaymentRequest {
	
	@NotBlank
	@Size(max = 100)
	private String transactionReference;

	@Size(max = 100)
	private String customerReference;

	@NotNull
	@DecimalMin(value = "0.0001")
	@Digits(integer = 15, fraction = 4)
	private BigDecimal amount;

	@NotBlank
	@Pattern(regexp = "[A-Z]{3}", message = "Currency must be a 3-letter uppercase code")
	private String currency;

	@NotBlank
	private String paymentStatus;

	private String paymentMethod;

	@NotNull
	private LocalDateTime paymentDate;
	public String getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getCustomerReference() {
		return customerReference;
	}
	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
