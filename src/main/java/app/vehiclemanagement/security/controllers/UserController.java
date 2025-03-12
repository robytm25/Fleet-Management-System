package app.vehiclemanagement.security.controllers;


import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.repositories.UserRepository;
import app.vehiclemanagement.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/security/userList")
    public String getUsers(Model model) {
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "/security/userList";
    }

    @GetMapping("/security/userList/{id}")
    @ResponseBody
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping(value = "users/addNew")
    public RedirectView addNew(User user, RedirectAttributes redir){
        Optional<User> user1 = userRepository.findByUsername(user.getUsername());
        if(user1.isPresent()){
            RedirectView redirectView = new RedirectView("/register", true);
            redir.addFlashAttribute("already", "Username Already Registered! Please choose another");
            return redirectView;
        } else {
            userService.save(user);
            RedirectView redirectView = new RedirectView("/login", true);
            redir.addFlashAttribute("message", "You successfully registered! You can now login");
            return redirectView;
        }

    }


    @PostMapping(value = "/security/userList")
    public String save(User user) {
        userService.save(user);
        return "redirect:/security/userList";
    }

    @RequestMapping(value = "/security/userList/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
       userService.delete(id);
        return "redirect:/security/userList";
    }

}






