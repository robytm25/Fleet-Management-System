package app.vehiclemanagement.fleet.services;

import app.vehiclemanagement.fleet.models.VehicleStatus;
import app.vehiclemanagement.fleet.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleStatusService {
    @Autowired
    VehicleStatusRepository vehicleStatusRepository;

    public List<VehicleStatus> getAll(){
        return vehicleStatusRepository.findAll();
    }

    public VehicleStatus getById(int id){
        return vehicleStatusRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleStatusRepository.deleteById(id);
    }

    public void save(VehicleStatus vehicleStatus){
        vehicleStatusRepository.save(vehicleStatus);
    }
}
