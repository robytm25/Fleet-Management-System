package app.vehiclemanagement.parameters.controllers;


import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.Location;
import app.vehiclemanagement.parameters.models.State;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.LocationService;
import app.vehiclemanagement.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@Controller
public class StateController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;


    @GetMapping("/states")
    public String getAll(Model model, String keyword) {
        List<State> states;
        states = keyword == null ? stateService.getAll():stateService.findByKeyword(keyword);
        model.addAttribute("states", states);
        return "/parameters/stateList";
    }

    @GetMapping("/states/{field}")
    public String getAllWithSort(Model model, @PathVariable String field, @PathParam("sortDir") String sortDir) {
        List<State> states;
        states = stateService.findAllBySort(field, sortDir);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("states", states);
        return "parameters/stateList";
    }

    @GetMapping("/stateAdd")
    public String addState(Model model) {
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        return "/parameters/stateAdd";
    }


    @GetMapping("/stateEdit/{id}")
    public String editState(@PathVariable Integer id, Model model) {
        State state = stateService.getById(id);
        model.addAttribute("state", state);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        return "/parameters/stateEdit";
    }

    @GetMapping("/stateDetails/{id}")
    public String detailsState(@PathVariable Integer id, Model model) {
        State state = stateService.getById(id);
        model.addAttribute("state", state);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<Location> locationList = locationService.getAll();
        model.addAttribute("locations", locationList);
        return "/parameters/stateDetails";
    }


    @PostMapping("/states")
    public String save(State state) {
        stateService.save(state);
        return "redirect:/states";
    }


    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        stateService.delete(id);
        return "redirect:/states";
    }

    @RequestMapping(value = "states/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(State state) {
        stateService.save(state);
        return "redirect:/states";
    }
}
