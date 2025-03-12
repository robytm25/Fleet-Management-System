package app.vehiclemanagement.parameters.services;

import app.vehiclemanagement.parameters.models.Supplier;
import app.vehiclemanagement.parameters.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }


    public Supplier getById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        supplierRepository.deleteById(id);
    }


    public void save(Supplier Supplier) {
        supplierRepository.save(Supplier);
    }
}
