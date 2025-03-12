package app.vehiclemanagement.parameters.repositories;

import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Query(value= "select s from  State s where " +
            "concat(s.name, s.capital, s.code) LIKE %?1%")
    List<State> getByKeyword(String keyword);
}

