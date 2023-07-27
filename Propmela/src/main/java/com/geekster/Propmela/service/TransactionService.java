package com.geekster.Propmela.service;

import com.geekster.Propmela.model.Transaction;
import com.geekster.Propmela.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    // Constructor injection
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        // Implement transaction creation logic with necessary validations
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        // Implement logic to retrieve a transaction by its ID
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        // Implement logic to update transaction data by ID
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction != null) {
            // Perform necessary updates to the existingTransaction object based on the provided transaction data
            // For example: existingTransaction.setAmount(transaction.getAmount());
            return transactionRepository.save(existingTransaction);
        }
        return null;
    }

    public boolean deleteTransaction(Long id) {
        // Implement logic to delete a transaction by ID
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transactionRepository.delete(transaction);
            return true;
        }
        return false;
    }

    // Implement other transaction-related service methods
}