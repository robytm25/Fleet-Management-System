package app.vehiclemanagement.parameters.controllers;

import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.State;
import app.vehiclemanagement.parameters.models.Supplier;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.StateService;
import app.vehiclemanagement.parameters.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SupplierController {
    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/suppliers")
    public String findAll(Model model) {
        List<Supplier> supplierList = supplierService.getAll();
        model.addAttribute("suppliers", supplierList);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/suppliersList";
    }

    @GetMapping("/parameters/supplier/{id}")
    @ResponseBody
    public Supplier getSupplier(@PathVariable Integer id) {
        return supplierService.getById(id);
    }


    @GetMapping("/supplierAdd")
    public String addSupplier(Model model) {
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        List<Supplier> supplierList = supplierService.getAll();
        model.addAttribute("suppliers", supplierList);
        return "/parameters/supplierAdd";
    }

    @GetMapping("/supplierEdit/{id}")
    public String editSupplier(@PathVariable Integer id, Model model) {
        Supplier supplier = supplierService.getById(id);
        model.addAttribute("supplier", supplier);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/supplierEdit";
    }

    @GetMapping("/supplierDetails/{id}")
    public String detailsSupplier(@PathVariable Integer id, Model model) {
        Supplier supplier = supplierService.getById(id);
        model.addAttribute("supplier", supplier);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/supplierDetails";
    }


    @PostMapping("/suppliers")
    public String save(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/suppliers/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/suppliers/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }
}
