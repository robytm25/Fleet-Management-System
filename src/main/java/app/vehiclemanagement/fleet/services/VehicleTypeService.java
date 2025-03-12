package app.vehiclemanagement.fleet.services;

import app.vehiclemanagement.fleet.models.VehicleType;
import app.vehiclemanagement.fleet.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeService {
    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleType> getAll(){
        return vehicleTypeRepository.findAll();
    }

    public VehicleType getById(int id){
        return vehicleTypeRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleTypeRepository.deleteById(id);
    }

    public void save(VehicleType vehicleType){
        vehicleTypeRepository.save(vehicleType);
    }
}
