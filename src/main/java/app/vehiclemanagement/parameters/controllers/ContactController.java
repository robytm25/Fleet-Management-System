package app.vehiclemanagement.parameters.controllers;

import app.vehiclemanagement.parameters.models.Contact;
import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.services.ContactService;
import app.vehiclemanagement.parameters.services.CountryService;
import app.vehiclemanagement.parameters.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public String findAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("states", stateService.getAll());
        model.addAttribute("contacts", contactService.getAll());
        return "/parameters/contactsList";
    }

    @GetMapping("/parameters/contact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Integer id) {
        return contactService.getById(id);
    }


    @GetMapping("/contactAdd")
    public String addContact() {
        return "/parameters/contactAdd";
    }

    @GetMapping("/contactEdit/{id}")
    public String editContact(@PathVariable Integer id, Model model) {
        Contact contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "/parameters/contactEdit";
    }

    @GetMapping("/contactDetails/{id}")
    public String detailsContact(@PathVariable Integer id, Model model) {
        Contact contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "/parameters/contactDetails";
    }


    @PostMapping("/contacts")
    public String save(Contact contact) {
        contactService.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        contactService.delete(id);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Contact contact) {
        contactService.save(contact);
        return "redirect:/contacts";
    }

}
