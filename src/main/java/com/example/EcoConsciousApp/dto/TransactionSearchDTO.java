package com.example.EcoConsciousApp.dto;

public class TransactionSearchDTO {
    private String transactionSearchCustomerId;

    public TransactionSearchDTO(String transactionSearchCustomerId) {
        this.transactionSearchCustomerId = transactionSearchCustomerId;
    }

    public String getTransactionSearchCustomerId() {
        return transactionSearchCustomerId;
    }

    public void setTransactionSearchCustomerId(String transactionSearchCustomerId) {
        this.transactionSearchCustomerId = transactionSearchCustomerId;
    }
}

