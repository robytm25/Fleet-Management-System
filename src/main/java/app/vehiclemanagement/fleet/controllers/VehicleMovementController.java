package app.vehiclemanagement.fleet.controllers;


import app.vehiclemanagement.fleet.models.VehicleMovement;
import app.vehiclemanagement.fleet.services.VehicleMovementService;
import app.vehiclemanagement.fleet.services.VehicleService;
import app.vehiclemanagement.parameters.models.Location;
import app.vehiclemanagement.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class VehicleMovementController {

    @Autowired private VehicleMovementService vehicleMovementService;
    @Autowired private VehicleService vehicleService;
    @Autowired private LocationService locationService;


    public Model addModelAttributes(Model model){
        model.addAttribute("vehicleMovements", vehicleMovementService.getAll());
        model.addAttribute("vehicles", vehicleService.getAll());
        model.addAttribute("locations", locationService.getAll());
        return model;
    }

    @GetMapping("/vehiclemovements")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/fleet/vehicleMovementList";
    }

    @GetMapping("/fleet/vehicleMovement/{id}")
    @ResponseBody
    public VehicleMovement getVehicleMovement(@PathVariable Integer id){
        return vehicleMovementService.getById(id);
    }

    @GetMapping("/vehicleMovementAdd")
    public String addVehicleMovement(Model model){
        addModelAttributes(model);
        List<Location> locationList = locationService.getAll();
        model.addAttribute("locations", locationList);
        return "/fleet/vehicleMovementAdd";
    }

    @GetMapping("/vehicleMovementEdit/{id}")
    public String editVehicleMovement(@PathVariable Integer id, Model model){
        VehicleMovement vehicleMovement = vehicleMovementService.getById(id);
        model.addAttribute("vehicleMovement", vehicleMovement);
        addModelAttributes(model);
        return "fleet/vehicleMovementEdit";
    }

    @GetMapping("/vehicleMovementDetails/{id}")
    public String detailsMovement(@PathVariable Integer id, Model model){
        VehicleMovement vehicleMovement = vehicleMovementService.getById(id);
        model.addAttribute("vehicleMovement",vehicleMovement);
        addModelAttributes(model);
        return "/fleet/vehicleMovementDetails";
    }

    @PostMapping("/vehiclemovements")
    public String addNew(VehicleMovement vehicleMovement){
        vehicleMovementService.save(vehicleMovement);
        return "redirect:/vehiclemovements";
    }
    @RequestMapping(value = "/vehicleMovements/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        vehicleMovementService.delete(id);
        return "redirect:/vehiclemovements";
    }

    @RequestMapping(value = "/vehicleMovements/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(VehicleMovement vehicleMovement){
        vehicleMovementService.save(vehicleMovement);
        return "redirect:/vehiclemovements";
    }

}
