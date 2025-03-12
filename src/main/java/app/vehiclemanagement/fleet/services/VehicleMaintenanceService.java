package app.vehiclemanagement.fleet.services;

import app.vehiclemanagement.fleet.models.VehicleMaintenance;
import app.vehiclemanagement.fleet.repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMaintenanceService {
    @Autowired
    VehicleMaintenanceRepository vehicleMaintenanceRepository;

    public List<VehicleMaintenance> getAll(){
        return vehicleMaintenanceRepository.findAll();
    }

    public VehicleMaintenance getById(int id){
        return vehicleMaintenanceRepository.findById(id).orElse(null);
    }

    public void delete(int id){
        vehicleMaintenanceRepository.deleteById(id);
    }

    public void save(VehicleMaintenance vehicleMaintenance){
        vehicleMaintenanceRepository.save(vehicleMaintenance);
    }
}
