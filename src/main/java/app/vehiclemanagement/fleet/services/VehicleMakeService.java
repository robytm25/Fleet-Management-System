package app.vehiclemanagement.fleet.services;


import app.vehiclemanagement.fleet.models.VehicleMake;
import app.vehiclemanagement.fleet.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMakeService {
    @Autowired
    VehicleMakeRepository vehicleMakeRepository;

    public List<VehicleMake> getAll(){
        return vehicleMakeRepository.findAll();
    }

    public VehicleMake getById(int id){
        return vehicleMakeRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleMakeRepository.deleteById(id);
    }

    public void save(VehicleMake vehicleMake){
        vehicleMakeRepository.save(vehicleMake);
    }
}
