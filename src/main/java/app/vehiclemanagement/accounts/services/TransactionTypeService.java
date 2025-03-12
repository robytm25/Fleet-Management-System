package app.vehiclemanagement.accounts.services;

import app.vehiclemanagement.accounts.models.TransactionType;
import app.vehiclemanagement.accounts.repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;



    public List<TransactionType> findAll(){
        return transactionTypeRepository.findAll();
    }


    public TransactionType findById(int id) {
        return transactionTypeRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        transactionTypeRepository.deleteById(id);
    }


    public void save( TransactionType transactionType) {
        transactionTypeRepository.save(transactionType);
    }
}
