package app.vehiclemanagement.fleet.services;

import app.vehiclemanagement.fleet.models.VehicleModel;
import app.vehiclemanagement.fleet.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelService {
    @Autowired
    VehicleModelRepository vehicleModelRepository;

    public List<VehicleModel> getAll(){
        return vehicleModelRepository.findAll();
    }

    public VehicleModel getById(int id){
        return vehicleModelRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleModelRepository.deleteById(id);
    }

    public void save(VehicleModel vehicleModel){
        vehicleModelRepository.save(vehicleModel);
    }
}
