package app.vehiclemanagement.parameters.repositories;

import app.vehiclemanagement.parameters.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query(value= "select l from  Location l where " +
            "concat(l.city, l.address, l.country, l.state) LIKE %?1%")
    List<Location> getByKeyword(String keyword);
}
