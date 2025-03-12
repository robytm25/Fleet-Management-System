package app.vehiclemanagement.fleet.controllers;


import app.vehiclemanagement.fleet.models.VehicleStatus;
import app.vehiclemanagement.fleet.services.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleStatusController {

    @Autowired
    private VehicleStatusService vehicleStatusService;

    @GetMapping("/fleet/vehicleStatusList")
    public String getAll(Model model) {
        List<VehicleStatus> vehicleStatusList = vehicleStatusService.getAll();
        model.addAttribute("vehicleStatuses",vehicleStatusList);
        return "/fleet/vehicleStatusList";
    }

    @GetMapping("/fleet/vehicleStatusList/{id}")
    @ResponseBody
    public VehicleStatus getById(@PathVariable Integer id) {
        return vehicleStatusService.getById(id);
    }

    @PostMapping(value = "/fleet/vehicleStatusList")
    public String save(VehicleStatus vehicleStatus) {
        vehicleStatusService.save(vehicleStatus);
        return "redirect:/fleet/vehicleStatusList";
    }

    @RequestMapping(value = "/fleet/vehicleStatusList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleStatusService.delete(id);
        return "redirect:/fleet/vehicleStatusList";
    }
}
