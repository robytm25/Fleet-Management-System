package app.vehiclemanagement.hr.controllers;

import app.vehiclemanagement.hr.models.JobTitle;
import app.vehiclemanagement.hr.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobTitleController {
    @Autowired
    private JobTitleService jobTitleService;

    @GetMapping("/hr/jobTitleList")
    public String getAll(Model model) {
        List<JobTitle> jobTitleList = jobTitleService.getAll();
        model.addAttribute("jobTitles", jobTitleList);
        return "/hr/jobTitleList";
    }

    @GetMapping("/hr/jobTitleList/{id}")
    @ResponseBody
    public JobTitle getById(@PathVariable Integer id) {
        return jobTitleService.getById(id);
    }

    @PostMapping(value = "/hr/jobTitleList")
    public String save(JobTitle jobTitle) {
        jobTitleService.save(jobTitle);
        return "redirect:/hr/jobTitleList";
    }

    @RequestMapping(value = "/hr/jobTitleList/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        jobTitleService.delete(id);
        return "redirect:/hr/jobTitleList";
    }
}
