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
public class LocationController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private StateService stateService;


    @GetMapping("/locations")
    public String getAll(Model model, String keyword) {
        List<Location> locations;
        locations = keyword == null ? locationService.getAll():locationService.findByKeyword(keyword);
        model.addAttribute("locations", locations);
        return "/parameters/locationList";
    }

    @GetMapping("/locations/{field}")
    public String getAllWithSort(Model model, @PathVariable String field, @PathParam("sortDir") String sortDir) {
        List<Location> locations;
        locations = locationService.findAllBySort(field, sortDir);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("locations", locations);
        return "parameters/locationList";
    }

    @GetMapping("/locationAdd")
    public String addLocation(Model model) {
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        List<Location> locationList = locationService.getAll();
        model.addAttribute("locations", locationList);
        return "/parameters/locationAdd";
    }


    @GetMapping("/locationEdit/{id}")
    public String editLocation(@PathVariable Integer id, Model model) {
        Location location = locationService.getById(id);
        model.addAttribute("location", location);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/locationEdit";
    }

    @GetMapping("/locationDetails/{id}")
    public String locationDetails(@PathVariable Integer id, Model model) {
        Location location = locationService.getById(id);
        model.addAttribute("location", location);
        List<Country> countryList = countryService.getAll();
        model.addAttribute("countries", countryList);
        List<State> stateList = stateService.getAll();
        model.addAttribute("states", stateList);
        return "/parameters/locationDetails";
    }


    @PostMapping("/locations")
    public String save(Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }


    @RequestMapping(value = "/locations/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        locationService.delete(id);
        return "redirect:/locations";
    }

    @RequestMapping(value = "locations/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }
}
