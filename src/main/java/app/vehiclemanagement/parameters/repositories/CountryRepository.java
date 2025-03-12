package app.vehiclemanagement.parameters.repositories;

import app.vehiclemanagement.parameters.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value= "select c from  Country c where " +
            "concat(c.description, c.continent, c.capital, c.code, c.nationality) LIKE %?1%")
    List<Country> getByKeyword(String keyword);

}
