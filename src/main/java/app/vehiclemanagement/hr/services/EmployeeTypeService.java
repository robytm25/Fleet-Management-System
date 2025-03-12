package app.vehiclemanagement.hr.services;

import app.vehiclemanagement.hr.models.EmployeeType;
import app.vehiclemanagement.hr.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;



    public List<EmployeeType> findAll(){
        return employeeTypeRepository.findAll();
    }


    public EmployeeType findById(int id) {
        return employeeTypeRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        employeeTypeRepository.deleteById(id);
    }


    public void save( EmployeeType employeeType) {
        employeeTypeRepository.save(employeeType);
    }
}
