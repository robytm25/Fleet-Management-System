package app.vehiclemanagement.security.controllers;

import app.vehiclemanagement.security.models.Role;
import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.services.RoleService;
import app.vehiclemanagement.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @GetMapping("/security/userEdit/{id}")
    public String editUser(@PathVariable Integer id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/userEdit";
    }


    @GetMapping("/security/roleList")
    public String getAll(Model model) {
        List<Role> roleList = roleService.findRoles();
        model.addAttribute("roles", roleList);
        return "/security/roleList";
    }

    @GetMapping("/security/roleList/{id}")
    @ResponseBody
    public Role getById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PostMapping(value = "/security/roleList")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/security/roleList";
    }

    @RequestMapping(value = "/security/roleList/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        roleService.delete(id);
        return "redirect:/security/roleList";
    }

    @RequestMapping("/security/roleList/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Integer userId, @PathVariable Integer roleId){
        roleService.assignUserRole(userId, roleId);
        return "redirect:/security/userEdit/" +userId;
    }

    @RequestMapping("/security/roleList/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Integer userId, @PathVariable Integer roleId){
        roleService.unassignUserRole(userId, roleId);
        return "redirect:/security/userEdit/" + userId;
    }
}
