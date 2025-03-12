package app.vehiclemanagement.hr.services;

import app.vehiclemanagement.hr.models.JobTitle;
import app.vehiclemanagement.hr.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleService {
    @Autowired
    private JobTitleRepository jobTitleRepository;

    public List<JobTitle> getAll() {
        return jobTitleRepository.findAll();
    }


    public JobTitle getById(Integer id) {
        return jobTitleRepository.findById(id).orElse(null);
    }


    public void delete(Integer id) {
        jobTitleRepository.deleteById(id);
    }


    public void save(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
    }

}
