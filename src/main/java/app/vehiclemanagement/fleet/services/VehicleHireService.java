package app.vehiclemanagement.fleet.services;


import app.vehiclemanagement.fleet.models.VehicleHire;
import app.vehiclemanagement.fleet.repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleHireService {
    @Autowired
    VehicleHireRepository vehicleHireRepository;
    
    public List<VehicleHire> getAll(){
        return vehicleHireRepository.findAll();
    }
    
    public VehicleHire getById(int id){
        return vehicleHireRepository.findById(id).orElse(null);
    }
    
    public void delete(int id){
        vehicleHireRepository.deleteById(id);
    }
    
    public void save(VehicleHire vehicleHire){
        vehicleHireRepository.save(vehicleHire);
    }
}
