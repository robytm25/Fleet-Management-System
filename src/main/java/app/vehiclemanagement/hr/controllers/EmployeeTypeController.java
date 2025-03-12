package app.vehiclemanagement.hr.controllers;

import app.vehiclemanagement.hr.models.EmployeeType;
import app.vehiclemanagement.hr.services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeTypeController {
    @Autowired
    private EmployeeTypeService employeeTypeService;

    @GetMapping("/hr/employeeTypeList")
    public String getAll(Model model) {
        List<EmployeeType> employeeTypeList = employeeTypeService.findAll();
        model.addAttribute("employeeTypes", employeeTypeList);
        return "/hr/employeeTypeList";
    }

    @GetMapping("/hr/employeeTypeList/{id}")
    @ResponseBody
    public EmployeeType getById(@PathVariable Integer id) {
        return employeeTypeService.findById(id);
    }

    @PostMapping(value = "/hr/employeeTypeList")
    public String save(EmployeeType employeeType) {
        employeeTypeService.save(employeeType);
        return "redirect:/hr/employeeTypeList";
    }

    @RequestMapping(value = "/hr/employeeTypeList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        employeeTypeService.delete(id);
        return "redirect:/hr/employeeTypeList";
    }
}
