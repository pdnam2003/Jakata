package com.example.lanchat.controller;

import com.example.lanchat.model.Application;
import com.example.lanchat.model.Job;
import com.example.lanchat.service.ApplicationService;
import com.example.lanchat.service.JobService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class ChatController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    public ChatController(JobService jobService, ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/jobs";
    }

    @GetMapping("/jobs")
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs";
    }

    @GetMapping("/jobs/new")
    public String showCreateJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "add-job";
    }

    @PostMapping("/jobs")
    public String saveJob(@Valid @ModelAttribute("job") Job job, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-job";
        }
        jobService.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteById(id);
        return "redirect:/jobs";
    }

    @GetMapping("/applications/new")
    public String showApplicationForm(@RequestParam(name = "jobId", required = false) Long jobId, Model model) {
        Application application = new Application();
        if (jobId != null) {
            jobService.findById(jobId).ifPresent(application::setJob);
        }
        model.addAttribute("application", application);
        model.addAttribute("jobs", jobService.findAll());
        return "add-application";
    }

    @PostMapping("/applications")
    public String submitApplication(@Valid @ModelAttribute("application") Application application,
                                    BindingResult result, Model model) {
        if (application.getJob() != null && application.getJob().getId() != null) {
            jobService.findById(application.getJob().getId()).ifPresent(application::setJob);
        }
        if (result.hasErrors()) {
            model.addAttribute("jobs", jobService.findAll());
            return "add-application";
        }
        applicationService.save(application);
        return "redirect:/applications/confirmation";
    }

    @GetMapping("/applications/confirmation")
    public String applicationConfirmation() {
        return "application-confirmation";
    }

    @GetMapping("/applications")
    public String listApplications(@RequestParam(name = "jobId", required = false) Long jobId, Model model) {
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("selectedJobId", jobId);
        if (jobId != null) {
            model.addAttribute("applications", applicationService.findByJobId(jobId));
        }
        return "applications";
    }

    @GetMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteById(id);
        return "redirect:/applications";
    }
}
