package app.vehiclemanagement.hr.services;

import app.vehiclemanagement.hr.models.Employee;
import app.vehiclemanagement.hr.models.JobTitle;
import app.vehiclemanagement.hr.repositories.EmployeeRepository;
import app.vehiclemanagement.hr.repositories.JobTitleRepository;
import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobTitleRepository jobTitleRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee findByUsername(String un) {
        return employeeRepository.findByUsername(un);
    }


    public void delete(int id) {
        employeeRepository.deleteById(id);
    }


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void assignUsername(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        User user = userRepository.findByFirstnameAndLastname(
                employee.getFirstname(),
                employee.getLastname());
        employee.setUsername(user.getUsername());
        employeeRepository.save(employee);
    }
}
