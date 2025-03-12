package app.vehiclemanagement.fleet.controllers;

import app.vehiclemanagement.fleet.models.Vehicle;
import app.vehiclemanagement.fleet.models.VehicleStatus;
import app.vehiclemanagement.fleet.services.*;
import app.vehiclemanagement.hr.services.EmployeeService;
import app.vehiclemanagement.parameters.models.Location;
import app.vehiclemanagement.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleTypeService vehicleTypeService;
    @Autowired
    private VehicleMakeService vehicleMakeService;
    @Autowired
    private VehicleStatusService vehicleStatusService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private VehicleModelService vehicleModelService;
    @Autowired
    private LocationService locationService;


    public Model addModelAttributes(Model model) {
        model.addAttribute("vehicles", vehicleService.getAll());
        model.addAttribute("vehicleTypes", vehicleTypeService.getAll());
        model.addAttribute("vehicleMakes", vehicleMakeService.getAll());
        model.addAttribute("vehicleStatuses", vehicleStatusService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("vehicleModels", vehicleModelService.getAll());
        model.addAttribute("locations", locationService.getAll());
        return model;
    }


    @GetMapping("/vehicles")
    public String findAll(Model model) {
        addModelAttributes(model);
        return "/fleet/vehicleList";
    }

    @GetMapping("/fleet/vehicle/{id}")
    @ResponseBody
    public Vehicle getVehicle(@PathVariable Integer id) {
        return vehicleService.getById(id);
    }

    @GetMapping("/vehicleAdd")
    public String addVehicle(Model model){
        addModelAttributes(model);
        List<Location> locationList = locationService.getAll();
        model.addAttribute("locations", locationList);
        List<VehicleStatus> vehicleStatusList = vehicleStatusService.getAll();
        model.addAttribute("vehicleStatuses", vehicleStatusList);
        return "/fleet/vehicleAdd";
    }

    @GetMapping("/vehicleEdit/{id}")
    public String editVehicle(@PathVariable Integer id, Model model){
        Vehicle vehicle = vehicleService.getById(id);
        model.addAttribute("vehicle", vehicle);
        addModelAttributes(model);
        return "fleet/vehicleEdit";
    }

    @GetMapping("/vehicleDetails/{id}")
    public String detailsVehicle(@PathVariable Integer id, Model model){
        Vehicle vehicle = vehicleService.getById(id);
        model.addAttribute("vehicle", vehicle);
        addModelAttributes(model);
        return "/fleet/vehicleDetails";
    }

    @PostMapping("/vehicles")
    public String addNew(Vehicle vehicle){
        vehicleService.save(vehicle);
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        vehicleService.delete(id);
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Vehicle vehicle){
        vehicleService.save(vehicle);
        return "redirect:/vehicles";
    }
}
