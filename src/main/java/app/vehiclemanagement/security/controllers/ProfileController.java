package app.vehiclemanagement.security.controllers;

import app.vehiclemanagement.hr.models.Employee;
import app.vehiclemanagement.hr.models.JobTitle;
import app.vehiclemanagement.hr.services.EmployeeService;
import app.vehiclemanagement.hr.services.JobTitleService;
import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.Location;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.LocationService;
import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired private EmployeeService employeeService;
    @Autowired private JobTitleService jobTitleService;
    @Autowired private CountryService countryService;
    @Autowired private LocationService locationService;
    @Autowired private UserService userService;


//    @GetMapping("/profile")
//    public String profilePage(Model model){
//        List<Employee> employeeList = employeeService.getAll();
//        model.addAttribute("employees", employeeList);
//        return "/profile";
//    }


    @RequestMapping(value = "/profile")
    public String profile(Model model, Principal principal){
            String un = principal.getName();
            model.addAttribute("employee", employeeService.findByUsername(un));
            List<Employee> employeeList = employeeService.getAll();
            model.addAttribute("employees", employeeList);
            List<JobTitle> jobTitleList = jobTitleService.getAll();
            model.addAttribute("jobTitles", jobTitleList);
            List<Country> countryList = countryService.getAll();
            model.addAttribute("countries", countryList);
            List<Location> locationList = locationService.getAll();
            model.addAttribute("locations", locationList);
            List<User> userList = userService.getAll();
            model.addAttribute("user", userList);
            return "profile";
    }

    @RequestMapping(value = "/guestsprofile")
    public String guestsProfile(Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "guestsprofile";
    }

}
