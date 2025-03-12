package app.vehiclemanagement.security.services;

import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.security.models.Role;
import app.vehiclemanagement.security.models.User;
import app.vehiclemanagement.security.repositories.RoleRepository;
import app.vehiclemanagement.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired private UserRepository userRepository;


    public void save(User user){
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public void delete(Integer id) {
       userRepository.deleteById(id);
    }

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}

