package app.vehiclemanagement.accounts.services;

import app.vehiclemanagement.accounts.models.TransactionStatus;
import app.vehiclemanagement.accounts.repositories.TransactionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionStatusService {

    @Autowired
    private TransactionStatusRepository transactionStatusRepository;


    public List<TransactionStatus> findAll(){
        return transactionStatusRepository.findAll();
    }


    public TransactionStatus findById(int id) {
        return transactionStatusRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        transactionStatusRepository.deleteById(id);
    }


    public void save( TransactionStatus transactionStatus) {
        transactionStatusRepository.save(transactionStatus);
    }
}
