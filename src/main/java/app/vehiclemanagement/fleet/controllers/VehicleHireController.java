package app.vehiclemanagement.fleet.controllers;

import app.vehiclemanagement.fleet.models.VehicleHire;
import app.vehiclemanagement.fleet.services.VehicleHireService;
import app.vehiclemanagement.fleet.services.VehicleService;
import app.vehiclemanagement.parameters.models.Client;
import app.vehiclemanagement.parameters.services.ClientService;
import app.vehiclemanagement.parameters.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleHireController {

    @Autowired private VehicleHireService vehicleHireService;
    @Autowired private VehicleService vehicleService;
    @Autowired private ClientService clientService;
    @Autowired private LocationService locationService;

    public Model addModelAttributes(Model model){
        model.addAttribute("vehicleHires", vehicleHireService.getAll());
        model.addAttribute("vehicles", vehicleService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("locations", locationService.getAll());
        return  model;
    }

    @GetMapping("/vehiclehires")
    public String findAll(Model model){
        addModelAttributes(model);
        return "/fleet/vehicleHireList";
    }

    @GetMapping("/fleet/vehicleHire/{id}")
    @ResponseBody
    public VehicleHire getVehicleHire(@PathVariable Integer id){
        return vehicleHireService.getById(id);
    }

    @GetMapping("/vehicleHireAdd")
    public String addVehicleHire(Model model){
        addModelAttributes(model);
        List<Client> clientList = clientService.getAll();
        model.addAttribute("clients", clientList);
        return "/fleet/vehicleHireAdd";
    }

    @GetMapping("/vehicleHireEdit/{id}")
    public String editVehicleHire(@PathVariable Integer id, Model model){
        VehicleHire vehicleHire =vehicleHireService.getById(id);
        model.addAttribute("vehicleHire", vehicleHire);
        addModelAttributes(model);
        return "/fleet/vehicleHireEdit";
    }

    @GetMapping("/vehicleHireDetails/{id}")
    public String detailsVehicleHire(@PathVariable Integer id, Model model){
        VehicleHire vehicleHire = vehicleHireService.getById(id);
        model.addAttribute("vehicleHire", vehicleHire);
        addModelAttributes(model);
        return "/fleet/vehicleHireDetails";
    }

    @PostMapping("/vehiclehires")
    public String addNew(VehicleHire vehicleHire){
        vehicleHireService.save(vehicleHire);
        return "redirect:/vehiclehires";
    }

    @RequestMapping(value = "/vehicleHires/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        vehicleHireService.delete(id);
        return "redirect:/vehiclehires";
    }

    @RequestMapping(value = "/vehicleHires/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(VehicleHire vehicleHire){
        vehicleHireService.save(vehicleHire);
        return "redirect:/vehiclehires";
    }
}
