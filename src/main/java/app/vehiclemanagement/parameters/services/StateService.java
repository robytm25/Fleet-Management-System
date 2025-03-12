package app.vehiclemanagement.parameters.services;

import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.State;
import app.vehiclemanagement.parameters.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> getAll() {
        return stateRepository.findAll();
    }

    public void save(State state) {
        stateRepository.save(state);
    }

    public void delete(Integer id) {
        stateRepository.deleteById(id);
    }


    public State getById(Integer id) {
        return stateRepository.findById(id).orElse(null);
    }

    public List<State> findByKeyword(String keyword){
        return stateRepository.getByKeyword(keyword);
    }

    public List<State> findAllBySort(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        return stateRepository.findAll(sort);
    }

}

