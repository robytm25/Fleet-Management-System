package app.vehiclemanagement.security.repositories;

import app.vehiclemanagement.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);

}
