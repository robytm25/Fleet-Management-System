package app.vehiclemanagement.fleet.controllers;

import app.vehiclemanagement.fleet.models.VehicleMake;
import app.vehiclemanagement.fleet.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @GetMapping("/fleet/vehicleMakeList")
    public String getAll(Model model) {
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAll();
        model.addAttribute("vehicleMakes",vehicleMakeList);
        return "/fleet/vehicleMakeList";
    }

    @GetMapping("/fleet/vehicleMakeList/{id}")
    @ResponseBody
    public VehicleMake getById(@PathVariable Integer id) {
        return vehicleMakeService.getById(id);
    }

    @PostMapping(value = "/fleet/vehicleMakeList")
    public String save(VehicleMake vehicleMake) {
        vehicleMakeService.save(vehicleMake);
        return "redirect:/fleet/vehicleMakeList";
    }

    @RequestMapping(value = "/fleet/vehicleMakeList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleMakeService.delete(id);
        return "redirect:/fleet/vehicleMakeList";
    }
}
