package app.vehiclemanagement.hr.services;

import app.vehiclemanagement.hr.models.EmployeeStatus;
import app.vehiclemanagement.hr.repositories.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeStatusService {

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;


    public List<EmployeeStatus> findAll(){
        return employeeStatusRepository.findAll();
    }


    public EmployeeStatus findById(int id) {
        return employeeStatusRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        employeeStatusRepository.deleteById(id);
    }


    public void save( EmployeeStatus employeeStatus) {
        employeeStatusRepository.save(employeeStatus);
    }
}
