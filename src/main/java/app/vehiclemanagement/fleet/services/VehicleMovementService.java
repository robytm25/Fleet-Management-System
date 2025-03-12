package app.vehiclemanagement.fleet.services;


import app.vehiclemanagement.fleet.models.VehicleMovement;
import app.vehiclemanagement.fleet.repositories.VehicleMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMovementService {
    @Autowired
    VehicleMovementRepository vehicleMovementRepository;

    public List<VehicleMovement> getAll(){
        return vehicleMovementRepository.findAll();
    }

    public VehicleMovement getById(int id){
        return vehicleMovementRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleMovementRepository.deleteById(id);
    }

    public void save(VehicleMovement vehicleMovement){
        vehicleMovementRepository.save(vehicleMovement);
    }
}
