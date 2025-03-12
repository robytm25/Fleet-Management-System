package app.vehiclemanagement.hr.controllers;

import app.vehiclemanagement.hr.models.Employee;
import app.vehiclemanagement.hr.services.EmployeeService;
import app.vehiclemanagement.hr.services.EmployeeStatusService;
import app.vehiclemanagement.hr.services.EmployeeTypeService;
import app.vehiclemanagement.hr.services.JobTitleService;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class EmployeeController {

    @Autowired private EmployeeService employeeService;
    @Autowired private StateService stateService;
    @Autowired private JobTitleService jobTitleService;
    @Autowired private EmployeeTypeService employeeTypeService;
    @Autowired private CountryService countryService;
    @Autowired private EmployeeStatusService employeeStatusService;

    public Model addModelAttributes(Model model){
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("states", stateService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("jobTitles", jobTitleService.getAll());
        model.addAttribute("employeeTypes", employeeTypeService.findAll());
        model.addAttribute("employeeStatuses", employeeStatusService.findAll());
        return model;
    }
    @GetMapping("/employees")
    public String findAll(Model model){
        addModelAttributes(model);
        return "hr/employeesList";
    }

    @GetMapping("/hr/employee/{id}")
    @ResponseBody
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @GetMapping("/employeeAdd")
    public String addEmployee(Model model){
        addModelAttributes(model);
        return "/hr/employeeAdd";
    }


    @GetMapping("/employeeEdit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        addModelAttributes(model);
        return "hr/employeeEdit";
    }

    @GetMapping("/employeeDetails/{id}")
    public String detailsEmployee(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        addModelAttributes(model);
        return "/hr/employeeDetails";
    }

    @PostMapping("/employees")
    public String addNew(Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value="/employees/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @RequestMapping(value="/employees/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/employees/assignUsername")
    public  String assignUsername(int id){
        employeeService.assignUsername(id);
        return "redirect:/employees";
    }

    @RequestMapping(value="/employee/profile")
    public String profile(Model model, Principal principal) {
        String un = principal.getName();
        addModelAttributes(model);
        model.addAttribute("employee", employeeService.findByUsername(un));
        return "profile";
    }
}
