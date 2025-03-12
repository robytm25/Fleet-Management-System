package app.vehiclemanagement.security.services;

import app.vehiclemanagement.security.models.Role;
import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.repositories.RoleRepository;
import app.vehiclemanagement.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }


    public Role getById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    //Assign Role to User
    public void assignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    //Unassign Role to User
    public void unassignUserRole(Integer userId, Integer roleId){
        User user  = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> x.getId()==roleId);
        userRepository.save(user);
    }

    public Set<Role> getUserNotRoles(User user){
        return roleRepository.getUserNotRoles(user.getId());
    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }

}