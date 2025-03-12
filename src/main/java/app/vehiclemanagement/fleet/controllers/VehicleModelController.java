package app.vehiclemanagement.fleet.controllers;

import app.vehiclemanagement.fleet.models.VehicleModel;
import app.vehiclemanagement.fleet.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping("/fleet/vehicleModelList")
    public String getAll(Model model) {
        List<VehicleModel> vehicleModelList = vehicleModelService.getAll();
        model.addAttribute("vehicleModels",vehicleModelList);
        return "/fleet/vehicleModelList";
    }

    @GetMapping("/fleet/vehicleModelList/{id}")
    @ResponseBody
    public VehicleModel getById(@PathVariable Integer id) {
        return vehicleModelService.getById(id);
    }

    @PostMapping(value = "/fleet/vehicleModelList")
    public String save(VehicleModel vehicleModel) {
        vehicleModelService.save(vehicleModel);
        return "redirect:/fleet/vehicleModelList";
    }

    @RequestMapping(value = "/fleet/vehicleModelList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        vehicleModelService.delete(id);
        return "redirect:/fleet/vehicleModelList";
    }
}
