package app.vehiclemanagement.accounts.services;

import app.vehiclemanagement.accounts.models.Transaction;
import app.vehiclemanagement.accounts.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }


    public Transaction getById(int id) {
        return transactionRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        transactionRepository.deleteById(id);
    }


    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
