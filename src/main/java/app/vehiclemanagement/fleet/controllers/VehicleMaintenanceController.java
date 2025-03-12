package app.vehiclemanagement.fleet.controllers;


import app.vehiclemanagement.fleet.models.VehicleMaintenance;
import app.vehiclemanagement.fleet.services.VehicleMaintenanceService;
import app.vehiclemanagement.fleet.services.VehicleService;
import app.vehiclemanagement.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMaintenanceController {

    @Autowired
    VehicleMaintenanceService vehicleMaintenanceService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    SupplierService supplierService;

    public Model addModelAttributes(Model model){
        model.addAttribute("vehicles", vehicleService.getAll());
        model.addAttribute("vehicleMaintenances", vehicleMaintenanceService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return  model;
    }

    @GetMapping("/vehiclemaintenances")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/fleet/vehicleMaintenanceList";
    }

    @GetMapping("/fleet/vehicleMaintenance/{id}")
    @ResponseBody
    public VehicleMaintenance getVehicleMaintenance(@PathVariable Integer id){
        return vehicleMaintenanceService.getById(id);
    }

    @GetMapping("/vehicleMaintenanceAdd")
    public String addVehicleMaintenance(Model model){
        addModelAttributes(model);
        return "/fleet/vehicleMaintenanceAdd";
    }

    @GetMapping("/vehicleMaintenanceEdit/{id}")
    public String editVehicleMaintenance(@PathVariable Integer id, Model model){
        VehicleMaintenance vehicleMaintenance =vehicleMaintenanceService.getById(id);
        model.addAttribute("vehicleMaintenance", vehicleMaintenance);
        addModelAttributes(model);
        return "fleet/vehicleMaintenanceEdit";
    }

    @GetMapping("/vehicleMaintenanceDetails/{id}")
    public String detailsVehicleMaintenance(@PathVariable Integer id, Model model){
        VehicleMaintenance vehicleMaintenance = vehicleMaintenanceService.getById(id);
        model.addAttribute("vehicleMaintenance", vehicleMaintenance);
        addModelAttributes(model);
        return "/fleet/vehicleMaintenanceDetails";
    }

    @PostMapping("/vehiclemaintenances")
    public String addNew(VehicleMaintenance vehicleMaintenance){
        vehicleMaintenanceService.save(vehicleMaintenance);
        return "redirect:/vehiclemaintenances";
    }

    @RequestMapping(value = "/vehicleMaintenances/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        vehicleMaintenanceService.delete(id);
        return "redirect:/vehiclemaintenances";
    }

    @RequestMapping(value = "/vehicleMaintenances/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(VehicleMaintenance vehicleMaintenance){
        vehicleMaintenanceService.save(vehicleMaintenance);
        return "redirect:/vehiclemaintenances";
    }
}
