package app.vehiclemanagement.fleet.controllers;


import app.vehiclemanagement.fleet.models.VehicleType;
import app.vehiclemanagement.fleet.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @GetMapping("/fleet/vehicleTypeList")
    public String getAll(Model model) {
        List<VehicleType> vehicleTypeList = vehicleTypeService.getAll();
        model.addAttribute("vehicleTypes",vehicleTypeList);
        return "/fleet/vehicleTypeList";
    }

    @GetMapping("/fleet/vehicleTypeList/{id}")
    @ResponseBody
    public VehicleType getById(@PathVariable Integer id) {
        return vehicleTypeService.getById(id);
    }

    @PostMapping(value = "/fleet/vehicleTypeList")
    public String save(VehicleType vehicleType) {
        vehicleTypeService.save(vehicleType);
        return "redirect:/fleet/vehicleTypeList";
    }

    @RequestMapping(value = "/fleet/vehicleTypeList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleTypeService.delete(id);
        return "redirect:/fleet/vehicleTypeList";
    }
}
