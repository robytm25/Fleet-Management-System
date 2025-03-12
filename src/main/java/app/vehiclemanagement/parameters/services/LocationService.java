package app.vehiclemanagement.parameters.services;

import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.Location;
import app.vehiclemanagement.parameters.models.State;
import app.vehiclemanagement.parameters.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void delete(Integer id) {
        locationRepository.deleteById(id);
    }


    public Location getById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    public List<Location> findByKeyword(String keyword){
        return locationRepository.getByKeyword(keyword);
    }

    public List<Location> findAllBySort(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        return locationRepository.findAll(sort);
    }
}
