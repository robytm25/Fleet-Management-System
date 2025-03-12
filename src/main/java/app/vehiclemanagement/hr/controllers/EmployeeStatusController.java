package app.vehiclemanagement.hr.controllers;

import app.vehiclemanagement.hr.models.EmployeeStatus;
import app.vehiclemanagement.hr.services.EmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeStatusController {
    @Autowired
    private EmployeeStatusService employeeStatusService;

    @GetMapping("/hr/employeeStatusList")
    public String getAll(Model model) {
        List<EmployeeStatus> employeeStatusList = employeeStatusService.findAll();
        model.addAttribute("employeeStatuses", employeeStatusList);
        return "/hr/employeeStatusList";
    }

    @GetMapping("/hr/employeeStatusList/{id}")
    @ResponseBody
    public EmployeeStatus getById(@PathVariable Integer id) {
        return employeeStatusService.findById(id);
    }

    @PostMapping(value = "/hr/employeeStatusList")
    public String save(EmployeeStatus employeeStatus) {
        employeeStatusService.save(employeeStatus);
        return "redirect:/hr/employeeStatusList";
    }

    @RequestMapping(value = "/hr/employeeStatusList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        employeeStatusService.delete(id);
        return "redirect:/hr/employeeStatusList";
    }
}
