package app.vehiclemanagement.fleet.services;

import app.vehiclemanagement.fleet.models.Vehicle;
import app.vehiclemanagement.fleet.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle getById(int id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleRepository.deleteById(id);
    }

    public void save(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }
}
