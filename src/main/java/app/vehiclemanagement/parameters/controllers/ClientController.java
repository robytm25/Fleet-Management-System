package app.vehiclemanagement.parameters.controllers;

import app.vehiclemanagement.parameters.models.Client;
import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.State;
import app.vehiclemanagement.parameters.services.ClientService;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public String findAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("states", stateService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "/parameters/clientsList";
    }

    @GetMapping("/parameters/client/{id}")
    @ResponseBody
    public Client getClient(@PathVariable Integer id) {
        return clientService.getById(id);
    }


    @GetMapping("/clientAdd")
    public String addClient(Model model) {
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/clientAdd";
    }

    @GetMapping("/clientEdit/{id}")
    public String editClient(@PathVariable Integer id, Model model) {
        Client client = clientService.getById(id);
        model.addAttribute("client", client);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/clientEdit";
    }

    @GetMapping("/clientDetails/{id}")
    public String detailsClient(@PathVariable Integer id, Model model) {
        Client client = clientService.getById(id);
        model.addAttribute("client", client);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/clientDetails";
    }


    @PostMapping("/clients")
    public String save(Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        clientService.delete(id);
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

}
